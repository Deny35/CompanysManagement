import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import { useEffect, useState } from "react";
import L from "leaflet";
import axios from "axios";
import "leaflet/dist/leaflet.css";

const customIcon = new L.Icon({
  iconUrl: "https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon.png",
  iconSize: [25, 41],
  iconAnchor: [12, 41],
  popupAnchor: [1, -34],
});

const Map = () => {
  const [companies, setCompanies] = useState<
    { id: number; name: string; latitude: number; longitude: number; lastMeeting: string | null }[]
  >([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/companies")
      .then((response) => {
        setCompanies(response.data);
      })
      .catch((error) => {
        console.error("Błąd pobierania firm:", error);
      });
  }, []);

  return (
    <MapContainer center={[52.2298, 21.0122]} zoom={6} style={{ height: "500px", width: "100%" }}>
      <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />

      {companies.map((company) => (
        <Marker key={company.id} position={[company.latitude, company.longitude]} icon={customIcon}>
          <Popup>
            <strong>{company.name}</strong>
            <br />
            Ostatnie spotkanie: {company.lastMeeting ? company.lastMeeting : "Brak danych"}
          </Popup>
        </Marker>
      ))}
    </MapContainer>
  );
};

export default Map;
