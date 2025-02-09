import { useEffect, useState } from "react";
import { Table, Container, Button } from "react-bootstrap";
import axios from "axios";
import { Link } from "react-router-dom";

interface Company {
  id: number;
  name: string;
  city: string;
}

const Companies = () => {
  const [companies, setCompanies] = useState<Company[]>([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/companies")
      .then(response => {
        console.log("Fetched companies:", response.data); // Wyświetlanie listy w konsoli
        setCompanies(response.data);
      })
      .catch(error => console.error("Error fetching companies:", error));
  }, []);

  return (
    <Container fluid className="mt-5">
      <h2 className="text-center mb-4">Company List</h2>
      <Table striped bordered hover responsive className="bg-dark text-white">
        <thead className="table-primary">
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>City</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {companies.map(company => (
            <tr key={company.id}>
              <td>{company.id}</td>
              <td>{company.name}</td>
              <td>{company.city}</td>
              <td>
                <Link to={`/companies/${company.id}`}>
                  <Button variant="light" size="sm">View</Button>
                </Link>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
};

export default Companies;
