import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus } from '@fortawesome/free-solid-svg-icons';

const AddTask = ({ newTask, setNewTask, addTask }) => {

    return (
        <>
            <div className="row">
                <div className="col">
                    <input
                        value={newTask}
                        onChange={(e) => setNewTask(e.target.value)}
                        className="form-control form-control-lg"
                        placeholder="Name..."
                        type="text"
                    />
                </div>
                <div className="col-auto">
                    <button
                        className="btn btn-lg btn-success"
                        onClick={addTask}
                    >
                        <FontAwesomeIcon className="icon" icon={faPlus} />
                        Add Item
                    </button>
                </div>
            </div>
            <br />
        </>
    )
}

export default AddTask;