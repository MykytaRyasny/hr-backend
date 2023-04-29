import logo from './logo.svg';
import './App.css';
import {Component} from "react";

class App extends Component {
  state = {
    employees: []
  };

  async componentDidMount() {
    const response = await fetch('/employee/all');
    const body = await response.json();
    this.setState({employees: body});
  }

  render() {
    const {employees} = this.state;
    return (
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <div className="App-intro">
              <h2>Employees</h2>
              {employees.map(employee =>
                  <div key={employee.dni}>
                    {employee.firstName} ({employee.lastName}, {employee.username})
                  </div>
              )}
            </div>
          </header>
        </div>
    );
  }
}
export default App;
