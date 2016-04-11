#!/usr/bin/env python

import os
import xml.etree.ElementTree
import csv

directory = "./maps"
filteredDistricts = ["02", "09", "11"]


def cutDistrictCodeFromPostCode(postCode):
    return postCode[1:3]
    

def hasElementNameWithAttribute(element, elementName, attributeName, attributeValue):
    for subElement in list(element):
        if subElement.tag == elementName:
            if attributeName in subElement.attrib:
                if subElement.attrib[attributeName] == attributeValue:
                    return True
    return False
    
    
def filterByDistrict(filterList, element, elementName, attributeName, attributeValue, attribute2Name):
    for subElement in list(element):
        if subElement.tag == elementName:
            if attributeName in subElement.attrib:
                if subElement.attrib[attributeName] == attributeValue:
                    if subElement.attrib[attribute2Name]:
                        postCode = subElement.attrib[attribute2Name]
                        if cutDistrictCodeFromPostCode(postCode) in filterList:
                            return True
    return False
    

def checkNode(x):
    hasCity = hasElementNameWithAttribute(x, "tag", "k", "addr:city")
    hasPostCode = hasElementNameWithAttribute(x, "tag", "k", "addr:postcode")
    hasStreet = hasElementNameWithAttribute(x, "tag", "k", "addr:street")
    isInRightDistrict = filterByDistrict(filteredDistricts, x, "tag", "k", "addr:postcode", "v")
    return hasCity and hasPostCode and hasStreet and isInRightDistrict


def checkWay(x):
    hasStreet = hasElementNameWithAttribute(x, "tag", "k", "addr:street")
    return hasStreet
    

INTERESTING_FIELDS = ["addr:city", "addr:postcode", "addr:street"]


def mapElement(element):
    d = dict()
    for subElement in list(element):
        if subElement.tag == "tag" and subElement.attrib["k"] in INTERESTING_FIELDS:
            fieldName = subElement.attrib["k"].split(":")[1]
            fieldValue = subElement.attrib["v"]
            if fieldName == "postcode":
                d['district'] = cutDistrictCodeFromPostCode(fieldValue)
            else:
                d[fieldName] = fieldValue
    return d
    

def getUtf8(x, attribute):
    return x.get(attribute).encode("utf-8")

def filterInterestingNode(root, elementName, callback):
    gen = [x for x in root.iter(elementName)]
    filtered = filter(lambda x: x is not None and callback(x), gen)
    mapped = map(mapElement, filtered)
    ordered = set((getUtf8(x, "city"), getUtf8(x, "district"), getUtf8(x, "street")) for x in mapped)
    return ordered


allInterestingStreets = set()
fileNames = os.listdir(directory)
for filePath in [os.path.abspath(os.path.join(directory, x)) for x in fileNames]:
    print(filePath)
    tree = xml.etree.ElementTree.parse(filePath)
    root = tree.getroot()
    streetsFromNodes = filterInterestingNode(root, "node", checkNode)
    streetsFromWays = filterInterestingNode(root, "way", checkNode)
    
    #print(streetsFromWays)    
    #break
    
    allInterestingStreets |= streetsFromNodes
    allInterestingStreets |= streetsFromWays
    #print(len(streetsFromNodes))

print(len(allInterestingStreets))
print(allInterestingStreets)


with open('streets.csv','wb') as out:
    csv_out=csv.writer(out)
    csv_out.writerow(['city', 'district', 'street'])
    csv_out.writerows(sorted(allInterestingStreets))
    #for row in allInterestingStreets:
    #    csv_out.writerow(row)
        


