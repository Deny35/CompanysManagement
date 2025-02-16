import { BrowserRouter as Router, Route, Routes, NavLink } from "react-router-dom";
import { Container, Navbar, Nav } from "react-bootstrap";
import Companies from "./pages/Companies";
import MapView from "./pages/MapView";
import AddCompany from "./pages/AddCompany";


function App() {
  return (
    <Router>
      {/* 🔹 Pasek nawigacyjny */}
      <Navbar bg="dark" variant="dark" expand="lg" fixed="top">
        <Container>
          <Navbar.Brand >Company Manager</Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <Nav.Link href="/">Lista</Nav.Link>
              <Nav.Link href="/map">Mapa</Nav.Link>
              <Nav.Link href="/add">Dodaj firmę</Nav.Link>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>

      <Container className="mt-4">
        <Routes>
          <Route path="/" element={<Companies />} />
          <Route path="/map" element={<MapView />} />
          <Route path="/add" element={<AddCompany />} />
        </Routes>
      </Container>
    </Router>
  );
}

export default App;
