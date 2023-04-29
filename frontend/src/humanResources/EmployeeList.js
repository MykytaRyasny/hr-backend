import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class EmployeeList extends Component {

    constructor(props) {
        super(props);
        this.state = {employees: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/employee/all')
            .then(response => response.json())
            .then(data => this.setState({employees: data}));
    }

    async remove(dni) {
        await fetch(`/employee/${dni}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'aplication/json'
            }
        }).then(() => {
            let updateEmployees = [...this.state.employees].filter(employee => employee.dni !== dni);
            this.setState({employees: updateEmployees});
        });
    }
    render() {
        const {employees, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>
        }

        const employeeList = employees.map(employee => {
            return <tr key = {employee.dni}>
                <td style={{whiteSpace: 'nowrap'}}>{employee.firstName}</td>
                <td>{employee.lastName}</td>
                <td>{employee.username}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/clients/" + employee.dni}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(employee.dni)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>

        });
        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/clients/new">Add Client</Button>
                    </div>
                    <h3>Employees</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Name</th>
                            <th width="30%">Email</th>
                            <th width="40%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {employeeList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        )
    }
}
export default EmployeeList;