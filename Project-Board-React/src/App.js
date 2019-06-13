import React from 'react'
import {BrowserRouter as Router, Route} from "react-router-dom"
import "bootstrap/dist/css/bootstrap.min.css"
import Navbar from "./components/Navbar"
import ProjectBoard from "./components/ProjectBoard"
import AddProjectTask from "./components/ProjectTask/AddProjectTask"
import { Provider } from "react-redux"
import store from "./store"
import UpdateProjectTask from './components/ProjectTask/UpdateProjectTask';

function App() {
  return (
    <Provider 
    store={store}
    >
      <Router>
        <div className="App">
          <Navbar />
          <Route exact path="/" component={ProjectBoard}/>
          <Route exact path="/addProjectTask" component={AddProjectTask}/>
          <Route exact path="/updateProjectTask/:pt_id" component={UpdateProjectTask}/>
        </div>
      </Router>
    </Provider>
  );
}

export default App;
