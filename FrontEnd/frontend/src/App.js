import React, { Component } from "react";
import {
  Route,
  BrowserRouter as Router,
  Switch,
  withRouter
} from "react-router-dom";
import Login from "./Login";
import Dashboard from "./Dashboard";
//import SignUp from "./SignUp";

class App extends Component {
  constructor(props) {
    super(props);

    // Store the previous pathname and search strings
    this.currentPathname = null;
    this.currentSearch = null;
  }

  componentDidMount() {
    const { history } = this.props;

    history.listen((newLocation, action) => {
      if (action === "PUSH") {
        if (
          newLocation.pathname !== this.currentPathname ||
          newLocation.search !== this.currentSearch
        ) {
          // Save new location
          this.currentPathname = newLocation.pathname;
          this.currentSearch = newLocation.search;

          // Clone location object and push it to history
          history.push({
            pathname: newLocation.pathname,
            search: newLocation.search
          });
        }
      } else {
        // Send user back if they try to navigate back
        history.go(1);
      }
    });
  }

  render() {
    return (
      <Router>
        <Switch>
          <Route path="/" exact={true} component={Login} />
          <Route path="/login" exact={true} component={Login} />
          <Route path="/dashboard" exact={true} component={Dashboard} />
          <Route path="/dashboard" exact={true} component={SignUp} />
        </Switch>
      </Router>
    );
  }
}

export default withRouter(App);
