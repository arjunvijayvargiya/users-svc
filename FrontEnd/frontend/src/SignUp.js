import React, { Component } from "react";
import AppNav from "./AppNav";
import "./App.css";
import "./Login.css";
import { Redirect } from "react-router";
import { Col, Button, Label, Input, Form, FormGroup } from "reactstrap";
import { withRouter } from "react-router";
import axios from "axios";
import { Container } from "react-bootstrap";
import "react-phone-number-input/style.css";
import PhoneInput from "react-phone-number-input";

class SignUp extends Component {
  constructor(props) {
    super(props);
    this.state = {
      userName: "",
      password: "",
      emailId: "",
      phoneNumber: "",
      redirect: false,
      errorLogin: false,
      errorReason: "",
    };

    this.handleChangeUsername = this.handleChangeUsername.bind(this);
    this.handleChangePassword = this.handleChangePassword.bind(this);
    this.handleChangeEmail = this.handleChangeEmail.bind(this);
    this.handleChangePhone = this.handleChangePhone.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChangeUsername(event) {
    this.setState({ userName: event.target.value });
  }

  handleChangePassword(event) {
    this.setState({ password: event.target.value });
  }

  handleChangeEmail(event) {
    this.setState({ emailId: event.target.value });
  }

  handleChangePhone(value) {
    this.setState({ phoneNumber: value });
  }

  async handleSubmit(event) {
    event.preventDefault();
    const requestObj = {
      userName: this.state.userName,
      password: this.state.password,
      emailId: this.state.emailId,
      phoneNumber: this.state.phoneNumber,
    };
    const jsStr = JSON.stringify(requestObj);
    console.log(jsStr);

    try {
      const response = await axios.post(
        "http://localhost:8282/api/v1/users",
        jsStr,
        { headers: { "Content-Type": "application/json" } }
      );
      // Success 🎉
      console.log("SignUp successful");
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

    if (this.state.redirect) {
      history.push("/login");
      return (
        <Redirect
          to={{
            pathname: "/login",
          }}
        />
      );
    }

    if (this.state.errorLogin) {
      errorText = <div className="errorText">{this.state.errorReason}</div>;
    }
    return (
      <div>
        <AppNav title="Expense Tracker" />
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
                  value={this.state.userName}
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

              <FormGroup>
                <Label for="emailId">Email</Label>
                <Input
                  type="emailId"
                  name="emailId"
                  id="emailId"
                  placeholder="type your email"
                  value={this.state.emailId}
                  onChange={this.handleChangeEmail}
                  required
                />
              </FormGroup>

              <FormGroup>
                <Label for="phoneNumber">Phone</Label>
                <PhoneInput
                  placeholder="Enter phone number"
                  value={this.state.phoneNumber}
                  onChange={this.handleChangePhone}
                />
              </FormGroup>

              <div className="horizontalSignup">
                <Button outline color="success" type="submit">
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

export default withRouter(SignUp);
