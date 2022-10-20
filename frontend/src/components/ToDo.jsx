import React from 'react';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCircleCheck, faPen, faTrashCan } from '@fortawesome/free-solid-svg-icons';


const ToDo = ({ toDo, markCompleted, setUpdateData, deleteTask, updateData }) => {
    return (
        <>
            {toDo && toDo
            .sort((a, b) => a.id > b.id ? 1 : -1)
            .map((task, index) => {
              return (
                <React.Fragment key={task.id}>
                  <div className="col taskBg">
  
                    <div className={task.flag === "CONCLUIDA" ? 'done' : ''}>
                      <span className="taskNumber">{index + 1}</span>
                      <span className="taskText">{task.descricao}</span>
                    </div>
  
                    <div className="iconsBox">
                      <span
                        className={task.flag === "CONCLUIDA" ? 'done' : 'check'}
                        title="Completed / Not Completed"
                        onClick={() => markCompleted(task)}>
                        <FontAwesomeIcon icon={faCircleCheck} />
                      </span>
  
                      {task.flag === "CONCLUIDA" ? null : (
                        <span
                          className={updateData ? 'click' : 'edit'}
                          title="Edit"
                          onClick={() => setUpdateData({
                            id: task.id,
                            descricao: task.descricao,
                            flag: task.flag
                          })}
                        >
                          <FontAwesomeIcon icon={faPen} />
                        </span>
                      )}
  
                      <span
                        className="delete"
                        title="Delete"
                        onClick={() => deleteTask(task.id)}>
                        <FontAwesomeIcon icon={faTrashCan} />
                      </span>
                    </div>
  
                  </div>
                </React.Fragment>
              )
            })}
        </>
    )
}

export default ToDo;