import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import { Container, Table } from "react-bootstrap";

interface ContactPerson {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  position: string;
}

interface Meeting {
  id: number;
  date: string;
  topic: string;
}

interface CompanyDetails {
  id: number;
  name: string;
  address: string;
  city: string;
  latitude: number;
  longitude: number;
  contactPersons: ContactPerson[];
  meetings: Meeting[];
}

const CompanyDetails: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const [company, setCompany] = useState<CompanyDetails | null>(null);

  useEffect(() => {
    const fetchCompanyDetails = async () => {
      try {
        const response = await axios.get<CompanyDetails>(`http://localhost:8080/api/companies/details/${id}`);
        setCompany(response.data);
      } catch (error) {
        console.error("Error fetching company details:", error);
      }
    };

    fetchCompanyDetails();
  }, [id]);

  if (!company) {
    return <Container><h3>Loading...</h3></Container>;
  }

  return (
    <Container>
      <h2>{company.name}</h2>
      <p><strong>Address:</strong> {company.address}, {company.city}</p>
      <p><strong>Location:</strong> {company.latitude}, {company.longitude}</p>

      {/* Tabela kontaktów */}
      <h3>Contact Persons</h3>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Position</th>
          </tr>
        </thead>
        <tbody>
          {company.contactPersons.length > 0 ? company.contactPersons.map((person) => (
            <tr key={person.id}>
              <td>{person.firstName} {person.lastName}</td>
              <td>{person.email}</td>
              <td>{person.phone}</td>
              <td>{person.position}</td>
            </tr>
          )) : <tr><td colSpan={4}>No contact persons</td></tr>}
        </tbody>
      </Table>

      {/* Tabela spotkań */}
      <h3>Meetings</h3>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Date</th>
            <th>Topic</th>
          </tr>
        </thead>
        <tbody>
          {company.meetings.length > 0 ? company.meetings.map((meeting) => (
            <tr key={meeting.id}>
              <td>{meeting.date}</td>
              <td>{meeting.topic}</td>
            </tr>
          )) : <tr><td colSpan={2}>No meetings</td></tr>}
        </tbody>
      </Table>
    </Container>
  );
};

export default CompanyDetails;
