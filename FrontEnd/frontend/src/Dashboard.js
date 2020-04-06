import React, { Component } from "react";
import AppNav from "./AppNav";
import { withRouter, Redirect } from "react-router";
import { Navbar } from "react-bootstrap";
import { NavDropdown } from "react-bootstrap";
import { Nav } from "react-bootstrap";
import { NavItem } from "react-bootstrap";
import { Button } from "react-bootstrap";
import { Form } from "react-bootstrap";

class Dashboard extends Component {
  constructor(props) {
    super(props);
    this.state = {
      // this is the most important step as it imparts a unique identity
      userName: props.location.state.id,
      loggedOut: false
    };

    this.handleLogOut = this.handleLogOut.bind(this);
    
  }

  handleLogOut(event) {
    this.setState({ loggedOut: true });
  }

  render() {
    const title = <h3>IronStudios Dashboard</h3>;
    const { history } = this.props;
    if (this.state.loggedOut) {
      history.push("/login");
      console.log("redirecting to login");
      return <Redirect to="/login" />;
    }

    return (
      <div>
        <Navbar bg="dark" variant="dark">
          <Navbar.Brand href="#home">Navbar</Navbar.Brand>
          <Nav className="mr-auto">
            <Nav.Link href="#home">Home</Nav.Link>
            <Nav.Link href="#features">Features</Nav.Link>
            <Nav.Link href="#pricing">Pricing</Nav.Link>
          </Nav>
          <Form inline>
            <Button outline color="success" type="button" onClick={this.handleLogOut}>LogOut</Button>
          </Form>
        </Navbar>
      </div>
    );
  }
}

export default withRouter(Dashboard);
