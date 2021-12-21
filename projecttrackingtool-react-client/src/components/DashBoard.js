// all the projects are going to be dislayed here.
// and form to create a new one.

import React, { Component } from 'react'
import ProjectItem from './Project/ProjectItem';
import "bootstrap/dist/css/bootstrap.min.css"


class DashBoard extends Component {
    render() {
        return (
            <React.Fragment>
                <h1 className="alert alert-info">Welcome to the Dashboard</h1>
                <ProjectItem />
                <ProjectItem />
                <ProjectItem />
            </React.Fragment>
        )
    }
}

export default DashBoard;

