import React, { Component } from 'react';
import AppNav from './AppNav';

class Dashboard extends Component{


    render() {
        const title = <h3>IronStudios Dashboard</h3>;
        return ( 
          <div>
            {title}  
            <AppNav />
          </div>
        );
      }

}

export default Dashboard;