import React from 'react'
import {BrowserRouter as Router, Route} from "react-router-dom"
import "bootstrap/dist/css/bootstrap.min.css"
import Navbar from "./components/Navbar"
import ProjectBoard from "./components/ProjectBoard"
import AddProjectTask from "./components/ProjectTask/AddProjectTask"
import { Provider } from "react-redux"
import store from "./store"

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
        </div>
      </Router>
    </Provider>
  );
}

export default App;
