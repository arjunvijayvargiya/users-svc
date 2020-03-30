import React, {Component } from 'react';
import {  Navbar, NavbarBrand, Nav} from 'reactstrap';
class AppNav extends Component {
    state = {
       
    }
     
  
    render() {
      
        return (<div>
            <Navbar color="dark" dark >
                <NavbarBrand href="/" className="Glacier">{this.props.title}</NavbarBrand>
                <Nav navbar className="ml-auto" >
                </Nav>
            </Navbar>
          </div> );
    }
}

export default AppNav;