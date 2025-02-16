import React, { useState, useEffect } from "react";
import axios from "axios";
import { Container, Table, Form, Button } from "react-bootstrap";

interface Company {
  id: number;
  name: string;
  city: string;
  latitude: number;
  longitude: number;
  lastMeetingDate?: string | null;
}

const Companies: React.FC = () => {
  const [companies, setCompanies] = useState<Company[]>([]);
  const [dateFilter, setDateFilter] = useState<string>("");

  useEffect(() => {
    fetchCompanies();
  }, []);

  const fetchCompanies = async (date?: string) => {
    try {
      const url = date ? `http://localhost:8080/api/companies/filter?startDate=${date}` : "http://localhost:8080/api/companies";
      const response = await axios.get<Company[]>(url);
      setCompanies(response.data);
    } catch (error) {
      console.error("Error fetching companies:", error);
    }
  };

  return (
    <Container>
      <h2 className="mt-4">Companies List</h2>

      <Form className="mb-3">
        <Form.Group controlId="dateFilter">
          <Form.Label>Filter by Last Meeting Date:</Form.Label>
          <Form.Control
            type="date"
            value={dateFilter}
            onChange={(e) => setDateFilter(e.target.value)}
          />
        </Form.Group>
        <Button className="mt-2" variant="primary" onClick={() => fetchCompanies(dateFilter)}>
          Filter
        </Button>
      </Form>

      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Name</th>
            <th>City</th>
            <th>Last Meeting</th>
          </tr>
        </thead>
        <tbody>
          {companies.map((company) => (
            <tr key={company.id}>
              <td>{company.name}</td>
              <td>{company.city}</td>
              <td>{company.lastMeetingDate ?? "No meetings"}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
};

export default Companies;
