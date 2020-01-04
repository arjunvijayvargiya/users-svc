import React, { Component } from "react";
import AppNav from "./AppNav";
import "./App.css";
import "./Login.css";
import { Redirect } from "react-router";
import { Container, Button, Label, Input, Form, FormGroup } from "reactstrap";
import axios from "axios";

class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      userName: "",
      password: "",
      redirect: false,
      errorLogin: false,
      errorReason: ""
    };

    this.handleChangeUsername = this.handleChangeUsername.bind(this);
    this.handleChangePassword = this.handleChangePassword.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChangeUsername(event) {
    this.setState({ userName: event.target.value });
  }

  handleChangePassword(event) {
    this.setState({ password: event.target.value });
  }

  async handleSubmit(event) {
    event.preventDefault();
    const requestObj = {
      userName: this.state.userName,
      password: this.state.password
    };
    const jsStr = JSON.stringify(requestObj);
    console.log(jsStr);

    try {
      const response = await axios.post(
        "http://localhost:8181/api/v1/authenticate",
        jsStr,
        { headers: { "Content-Type": "application/json" } }
      );
      // Success 🎉
      console.log("Login successful");
      this.setState({ redirect: true });
    } catch (error) {
      console.log(
        "ERROR %s",
        JSON.stringify(error.response.data.response)
      );
      this.setState({ errorLogin: true });
      this.setState({ errorReason: error.response.data.response });
    }
  }

  render() {
    let errorText = <div></div>;
    if (this.state.redirect) {
      return <Redirect to="/dashboard" />;
    }

    if (this.state.errorLogin) {
      errorText = <div className="errorText">{this.state.errorReason}</div>;
    }
    return (
      <div>
        <AppNav />
        <Container>
          <div className="spacer"></div>
          <Form onSubmit={this.handleSubmit}>
            <FormGroup>
              <Label for="userName">Username</Label>
              <Input
                type="text"
                name="text"
                id="userName"
                placeholder="type your username"
                value={this.state.value}
                onChange={this.handleChangeUsername}
              />
            </FormGroup>
            <FormGroup>
              <Label for="password">Password</Label>
              <Input
                type="password"
                name="password"
                id="password"
                placeholder="type your password"
                value={this.state.password}
                onChange={this.handleChangePassword}
              />
            </FormGroup>
            <Button color="success" type="submit">
              Login
            </Button>{" "}
            {errorText}
          </Form>
        </Container>
      </div>
    );
  }
}

export default Login;
