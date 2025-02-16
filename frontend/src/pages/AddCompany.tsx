import { useState } from "react";
import { Form, Button, Container, Alert } from "react-bootstrap";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const AddCompany = () => {
  const [company, setCompany] = useState({
    name: "",
    address: "",
    city: "",
    zipCode: "",
  });

  const [error, setError] = useState("");
  const [success, setSuccess] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setCompany({ ...company, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError("");
    setSuccess(false);

    try {
        const response = await axios.post("http://localhost:8080/api/companies", company);
        if (response.status === 201) {
            setSuccess(true);
            setTimeout(() => navigate("/"), 2000);
        }
    } catch (err) {
        setError("Wystąpił błąd podczas dodawania firmy.");
    }
};


  return (
    <Container className="mt-5">
      <h2>Dodaj nową firmę</h2>
      {error && <Alert variant="danger">{error}</Alert>}
      {success && <Alert variant="success">Firma została dodana pomyślnie!</Alert>}
      
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="name">
          <Form.Label>Nazwa firmy</Form.Label>
          <Form.Control type="text" name="name" value={company.name} onChange={handleChange} required />
        </Form.Group>

        <Form.Group controlId="address">
          <Form.Label>Adres</Form.Label>
          <Form.Control type="text" name="address" value={company.address} onChange={handleChange} required />
        </Form.Group>

        <Form.Group controlId="city">
          <Form.Label>Miasto</Form.Label>
          <Form.Control type="text" name="city" value={company.city} onChange={handleChange} required />
        </Form.Group>

        <Form.Group controlId="zipCode">
          <Form.Label>Kod pocztowy</Form.Label>
          <Form.Control type="text" name="zipCode" value={company.zipCode} onChange={handleChange} required />
        </Form.Group>

        <Button variant="primary" type="submit" className="mt-3">
          Dodaj firmę
        </Button>
      </Form>
    </Container>
  );
};

export default AddCompany;
