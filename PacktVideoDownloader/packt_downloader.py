import requests
import os
import re

collectedUrls = []

dirpath = "."
dirs = [ name for name in os.listdir(dirpath) if os.path.isdir(os.path.join(dirpath, name)) ]
for directory in dirs:
    files = [ name for name in os.listdir(directory) if os.path.isfile(os.path.join(directory, name)) ]
    i = 0
    for file in files:    
        print("Processing: " + directory + "/" + file)        
        with open(os.path.join(directory, file), 'r') as content_file:
            content = content_file.read()            
            results = re.findall('<video .* src="(.*)" .*</video>', content)
            print("\tfound: " + str(len(results)))
            collectedUrls = collectedUrls + results
            i = i + len(results)
    print("\nFound: " + str(i) + "/" + str(len(files)) + " : \n" + str(files))
            
    
print()

refinedUrls = []

for url in collectedUrls:
    ref1 = url.replace("&amp;", "&")
    ref2 = ref1.replace(" ", "%20")
    refinedUrls.append(ref2)
    #print("\n"+ref2)
    
    
i = 0    
for link in refinedUrls:
    i = i + 1
    print("\nDownloading: " + link)
    fileNames = re.findall( ".*/videos/(.*)\?", link)
    headers = {'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36'}
    result = requests.get(link, headers=headers)
    with open(fileNames[0], 'wb', encoding='utf8') as f:
        f.write(result.content)
        
