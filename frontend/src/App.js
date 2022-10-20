import React, { useEffect, useState } from 'react';

import axios from 'axios';

import AddTask from './components/AddTask.jsx';
import UpdateTask from './components/UpdateTask.jsx';
import ToDo from './components/ToDo.jsx';

import 'bootstrap/dist/css/bootstrap.min.css';


import './App.css';

function App() {

  const [toDo, setToDo] = useState([]);
  const [newTask, setNewTask] = useState('');
  const [updateData, setUpdateData] = useState('');


  async function list() {
    await axios('http://localhost:8080/todolist').then(response => {
      setToDo(response.data)
    })
  }

  useEffect(() => {
    list();
  }, [])

  async function addTask() {
    // Validação
    if (!newTask) {
      return;
    }

    try {
      await axios.post('http://localhost:8080/todolist', {
        descricao: newTask,
        flag: 0
      });

      list();
      // alert('Tarefa criada com sucesso!');
      setNewTask('');
    } catch (err) {
      console.log(err);
      alert('Erro ao criar a tarefa!');
    }
  }

  async function deleteTask(id) {
    await axios.delete(`http://localhost:8080/todolist/${id}`);
    list();
  }

  async function markCompleted(task) {
    await axios.put(`http://localhost:8080/todolist/${task.id}/status`, {
      id: task.id,
      flag: task.flag === 0 ? 1 : 0
    });
    list();
  }

  const cancelUpdate = () => {
    setUpdateData('');
  }

  const changeTask = (e) => {
    let newEntry = {
      id: updateData.id,
      descricao: e.target.value,
      flag: updateData.flag
    }
    setUpdateData(newEntry);
  }

  async function updateTask() {
    await axios.put(`http://localhost:8080/todolist`, {
      id: updateData.id,
      descricao: updateData.descricao,
      flag: updateData.flag
    })
    setUpdateData('');
    list();
  }

  // PAGE
  return (
    <>
    <section className="heading-section">
      <h1 className="title">To Do List</h1>
    </section>

    <div className="container App">
      <h3 className="label">New Task</h3>
      {updateData && updateData ?
        (
          <UpdateTask 
          updateData={updateData} 
          updateTask={updateTask}
          cancelUpdate={cancelUpdate}
          changeTask={changeTask}
          />
        ) : (
          <AddTask 
            newTask={newTask}
            setNewTask={setNewTask}
            addTask={addTask}
          />
      )}

      {toDo && toDo.length ? '' : 'No Tasks...'}

      <ToDo 
        toDo={toDo} 
        markCompleted={markCompleted} 
        setUpdateData={setUpdateData}
        updateData={updateData}
        deleteTask={deleteTask}
      />
    </div>
    </>
  );
}

export default App;
