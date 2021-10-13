from flask import Flask, jsonify, abort, request
app = Flask(__name__)

tasks = [
    {
        'id': 1,
        'title': u'Milk',
        'done': False
    },
    {
        'id': 2,
        'title': u'Learn Python',
        'description': u'Need to find a good Python', 
        'done': False
    }
]

@app.route("/")
def hello():
	return "Ekarat"

#http://localhost:5000/todo/api/v1.0/tasks 
@app.route('/todo/api/v1.0/tasks', methods=['GET']) 
def get_tasks():
    return jsonify({'tasks': tasks})

# http://localhost:5000/todo/api/v1.0/tasks/2 
@app.route('/todo/api/v1.0/tasks/<int:task_id>', methods=['GET']) 
def get_task(task_id):
    task = [task for task in tasks if task['id'] == task_id] 
    if len(task) == 0:
        abort(404)
    return jsonify({'tasks': task[0]})

@app.route('/todo/api/v1.0/tasks', methods=['POST']) 
def create_task(): 

    if not request.json or not 'title' in request.json: 
        abort(400)
    
    task = { 
        'id': tasks[-1]['id'] + 1, 
        'title': request.json['title'], 
        'description': request.json.get('description',''), 
        'done': False 
    } 
    
    tasks.append(task) 
    return jsonify({'task': task}), 201

if __name__ == '__main__':
	app.run(host= '0.0.0.0')



PTR5020626
b8b51548a12849779c3aa8a49ea464dc
KB366662588027
KB366662588027