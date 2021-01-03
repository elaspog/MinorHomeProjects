import os
from flask import Flask, render_template, request
from werkzeug.utils import secure_filename


app = Flask(__name__)

app.config['UPLOAD_FOLDER'] = "uploaded"

@app.route('/', methods = ['GET', 'POST'])
def upload_file():

    if request.method == 'GET':
        return render_template('index.html')

    elif request.method == 'POST':
        f = request.files['file']
        filepath = os.path.join(os.path.dirname(os.path.abspath(__file__)), app.config['UPLOAD_FOLDER'], secure_filename(f.filename))
        f.save(filepath)
        os.system("bash cli_executor.sh %s" % filepath)
        return render_template('index.html', file=filepath)

if __name__ == '__main__':
    # app.run(debug = True)
    app.run(host="0.0.0.0", port=8080)
    # app.run()
