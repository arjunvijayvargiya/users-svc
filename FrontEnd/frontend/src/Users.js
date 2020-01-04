import React, {Component } from 'react';
import AppNav from './AppNav';
class Users extends Component {
    state = {
         isLoading : true,
         users : []
    }
     
    async componentDidMount(){
       const response = await fetch('/api/v1/users') 
       const body = await response.json();
       this.setState({users : body , isLoading : false});
    }

    render() {
        const {users, isLoading} = this.state;
        if(isLoading)
           return (<div>Loading ....</div>);
        return ( 
            <div>
                        
               <AppNav/>
                <h2> USERS</h2>
                {
                   users.map( user =>
                      <div id= {user.userName}>
                          {user.userName}
                      </div>
                   )
                }
            </div>
         );
    }
}

export default Users;