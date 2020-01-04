import React, {Component } from 'react';
import {  Navbar, NavbarBrand, Nav, NavItem, NavLink } from 'reactstrap';
class AppNav extends Component {
    state = {
       
    }
     
  
    render() {
      
        return (<div>
            <Navbar color="dark" dark  expand="md">
              <NavbarBrand href="/" className="mr-auto">USERS APPLICATION</NavbarBrand>
                <Nav navbar className="ml-auto" >
                  <NavItem>
                    <NavLink href="/">Login</NavLink>
                  </NavItem>
                  <NavItem>
                    <NavLink href="/users">Users</NavLink>
                  </NavItem>
                </Nav>
            </Navbar>
          </div> );
    }
}

export default AppNav;