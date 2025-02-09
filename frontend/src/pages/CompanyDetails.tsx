import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import { Card } from "react-bootstrap";

interface Company {
  id: number;
  name: string;
  address: string;
  city: string;
  zipCode: string;
  latitude: number;
  longitude: number;
}

const CompanyDetails = () => {
  const { id } = useParams<{ id: string }>();
  const [company, setCompany] = useState<Company | null>(null);

  useEffect(() => {
    axios.get(`http://localhost:8080/api/companies/${id}`)
      .then(response => setCompany(response.data))
      .catch(error => console.error("Error fetching company details:", error));
  }, [id]);

  if (!company) return <p>Loading...</p>;

  return (
    <Card>
      <Card.Body>
        <Card.Title>{company.name}</Card.Title>
        <Card.Text>
          Address: {company.address}, {company.city}, {company.zipCode}
        </Card.Text>
        <Card.Text>Latitude: {company.latitude}, Longitude: {company.longitude}</Card.Text>
      </Card.Body>
    </Card>
  );
};

export default CompanyDetails;
