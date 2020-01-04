import React, { Component } from 'react';
import {Route, BrowserRouter as Router, Switch} from 'react-router-dom';
import Login from './Login';
import Users from './Users';
import Dashboard from './Dashboard';

class App extends Component{
   state = { }
   render(){
      return ( <Router>
            <Switch>
                <Route path='/' exact={true} component={Login}/>
                <Route path='/users' exact={true} component={Users}/>
                <Route path='/dashboard' exact={true} component={Dashboard}/>
            </Switch>
       </Router>
      );
   }

}

export default App;