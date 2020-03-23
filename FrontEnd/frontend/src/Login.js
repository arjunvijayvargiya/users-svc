import React, { Component } from "react";
import AppNav from "./AppNav";
import "./App.css";
import "./Login.css";
import { Redirect } from "react-router";
import { Col, Button, Label, Input, Form, FormGroup } from "reactstrap";
import { withRouter } from "react-router";
import axios from "axios";
import { Container } from "react-bootstrap";

class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      userName: "",
      password: "",
      redirect: false,
      errorLogin: false,
      errorReason: "",
      toSignUp: false
    };

    this.handleChangeUsername = this.handleChangeUsername.bind(this);
    this.handleChangePassword = this.handleChangePassword.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleSignUp = this.handleSignUp.bind(this);
  }

  handleChangeUsername(event) {
    this.setState({ userName: event.target.value });
  }

  handleChangePassword(event) {
    this.setState({ password: event.target.value });
  }

  handleSignUp(event) {
    this.setState({ toSignUp: true });
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
      console.log("ERROR %s", JSON.stringify(error.response.data.response));
      this.setState({ errorLogin: true });
      this.setState({ errorReason: error.response.data.response });
    }
  }

  async componentDidMount() {}

  render() {
    let errorText = <div></div>;

    const { history } = this.props;

    if (this.state.toSignUp) {
      history.push("/signUp");
      console.log("redirecting to signup");
      return <Redirect to="/signUp" />;
    }
    if (this.state.redirect) {
      history.push("/dashboard");
      return (
        <Redirect
          to={{
            pathname: "/dashboard",
            state: { id: this.state.userName }
          }}
        />
      );
    }

    if (this.state.errorLogin) {
      errorText = <div className="errorText">{this.state.errorReason}</div>;
    }
    return (
      <div>
        <AppNav title="Expense Tracker"/>
        <Container>
          <div className="spacer"></div>
          <Form onSubmit={this.handleSubmit}>
            <Col md={6}>
              <FormGroup>
                <Label for="userName">Username</Label>
                <Input
                  type="text"
                  name="text"
                  id="userName"
                  placeholder="type your username"
                  value={this.state.value}
                  onChange={this.handleChangeUsername}
                  required
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
                  required
                />
              </FormGroup>
              <div className="horizontalSignup">
                <Button outline color="success" type="submit">
                  Login
                </Button>
                <Label className="NewToAppLabel">New to application?</Label>
                <Button
                  formNoValidate
                  type="button"
                  onClick={this.handleSignUp}
                  color="link"
                >
                  SignUp
                </Button>{" "}
              </div>
              {errorText}
            </Col>
          </Form>
        </Container>
      </div>
    );
  }
}

export default withRouter(Login);
