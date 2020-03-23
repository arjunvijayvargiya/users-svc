import React, { Component } from 'react';
import AppNav from './AppNav';
import { withRouter } from "react-router";

class Dashboard extends Component{

    render() {
        const title = <h3>IronStudios Dashboard</h3>;
        return ( 
          <div>
            {title}  
            <AppNav />
            <h3>Hello {this.props.location.state.id}</h3>
          </div>
        );
      }

}

export default withRouter(Dashboard);