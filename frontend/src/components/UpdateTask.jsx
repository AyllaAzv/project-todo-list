import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faArrowRotateRight, faXmark } from '@fortawesome/free-solid-svg-icons';

const UpdateTask = ({ updateData, updateTask, cancelUpdate, changeTask }) => {
    return (
        <>
            <br />
            <div className="row">
              <div className="col">
                <input
                  value={updateData && updateData.descricao}
                  onChange={(e) => changeTask(e)}
                  className="form-control form-control-lg"
                />
              </div>
              <div className="col-auto">
                <button
                  onClick={updateTask}
                  className="btn btn-lg btn-success mr-20">
                  <FontAwesomeIcon className="icon" icon={faArrowRotateRight}/>
                  Update
                </button>
                <button
                  onClick={cancelUpdate}
                  className="btn btn-lg btn-warning">
                  <FontAwesomeIcon className="icon" icon={faXmark} />
                  Cancel
                </button>
              </div>
            </div>
            <br />
        </>
    )
}

export default UpdateTask;