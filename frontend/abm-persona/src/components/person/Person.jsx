import React from "react";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import config from "../../config.json";
import axios from "axios";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Accordion from "@mui/material/Accordion";
import AccordionDetails from "@mui/material/AccordionDetails";
import AccordionSummary from "@mui/material/AccordionSummary";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import TextField from "@mui/material/TextField";
import { DemoContainer } from "@mui/x-date-pickers/internals/demo";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import dayjs from "dayjs";


const Person = () => {
  const navigate = useNavigate();
  const [expanded, setExpanded] = useState(false);
  const [selectedfecha_nacimiento, setFechaNacimiento] = useState(null);
  const { id } = useParams();
  const [tipo_documento, setTipoDocumento] = useState([]);
  const [selectedNombre, setselectedNombre] = useState("");
  const [selectedApellido, setselectedApellido] = useState("");
  const [selectedNumero, setselectedNumero] = useState("");
  const [selectedTipoDocumento, setselectedTipoDocumento] = useState({});

  useEffect(() => {
    if (!id) return;
    if (id !== "new") {
      console.log(id);
      const fetchPerson = async () => {
        const { data } = await axios.get(
          `${config.apiUrlPerson}get-persona/${id}`
        );
        setselectedNombre(data.nombre);
        setselectedApellido(data.apellido);
        setselectedNumero(data.numero_documento);
        setFechaNacimiento(dayjs(data.fecha_nacimiento));
        setselectedTipoDocumento(data.tipo_documento);
      };
      fetchPerson();
    }

    const fetchTipoDocumento = async () => {
      const res = await axios.get(
        `${config.apiUrlDocumento}get-tipo-documento`
      );
      setTipoDocumento(res.data);
    };
    fetchTipoDocumento();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    let objSubmit = {};
    if (selectedNombre) {
      objSubmit.nombre = selectedNombre;
    }

    if (selectedApellido) {
      objSubmit.apellido = selectedApellido;
    }

    if (selectedNumero) {
      objSubmit.numero_documento = selectedNumero;
    }

    if (selectedfecha_nacimiento) {
      objSubmit.fecha_nacimiento = dayjs(selectedfecha_nacimiento).format(
        "YYYY-MM-DD"
      );
    }
    if (selectedTipoDocumento) {
      objSubmit.tipo_documento = selectedTipoDocumento;
    }

    if (id === "new") {
      await axios.post(`${config.apiUrlPerson}create`, objSubmit);
      return navigate("/");
    } else {
      await axios.put(`${config.apiUrlPerson}update/${id}`, objSubmit);
      return navigate("/");
    }
  };

  const handleClen = () => {
    setselectedNombre("");
    setselectedApellido("");
    setselectedNumero("");
    setFechaNacimiento(null);
    setselectedTipoDocumento({});
  };

  const handleChange = (panel) => (event, isExpanded) => {
    setExpanded(isExpanded ? panel : false);
  };

  const handleSelectChange = (event) => {
    const option = tipo_documento.find(
      (o) => o.nombre_tipo_documento === event.target.value
    );
    setselectedTipoDocumento(option);
  };

  const handleNombreChange = (event) => {
    setselectedNombre(event.target.value);
  };

  const handleApellidoChange = (event) => {
    setselectedApellido(event.target.value);
  };

  const handleNumeroChange = (event) => {
    setselectedNumero(event.target.value);
  };

  return (
    <div>
      <Box sx={{ flexGrow: 1 }}>
        <AppBar position="static" style={{ background: "#e0e0e0" }}>
          <Toolbar variant="dense">
            <Typography
              variant="h6"
              component="div"
              sx={{ flexGrow: 1, color: "#000" }}
            >
              Persona
            </Typography>
            <Button
              variant="contained"
              onClick={() => navigate("/")}
              color="success"
            >
              VOLVER
            </Button>
          </Toolbar>
        </AppBar>
      </Box>
      <Box sx={{ margin: "50px" }}>
        <Accordion
          expanded={expanded === "panel4"}
          onChange={handleChange("panel4")}
          sx={{ backgroundColor: "#f5f5f5" }}
        >
          <AccordionSummary
            expandIcon={<ExpandMoreIcon />}
            aria-controls="panel4bh-content"
            id="panel4bh-header"
          >
            <Typography
              sx={{ width: "33%", flexShrink: 0, fontSize: 20, color: "#000" }}
            >
              Datos Personales
            </Typography>
          </AccordionSummary>
          <AccordionDetails>
            <div style={{ display: "flex", marginBottom: 30 }}>
              <div style={{ width: "20%", marginRight: 50 }}>
                <div
                  style={{
                    display: "flex",
                    fontSize: 16,
                    margin: 5,
                  }}
                >
                  Nombre
                </div>
                <TextField
                  value={selectedNombre}
                  size="small"
                  onChange={handleNombreChange}
                  fullWidth
                />
              </div>
              <div style={{ width: "20%", marginRight: 50 }}>
                <div
                  style={{
                    display: "flex",
                    fontSize: 16,
                    margin: 5,
                  }}
                >
                  Apellido
                </div>
                <TextField
                  value={selectedApellido}
                  size="small"
                  onChange={handleApellidoChange}
                  fullWidth
                />
              </div>

              <div style={{ width: "20%", marginRight: 50 }}>
                <div
                  style={{
                    display: "flex",
                    fontSize: 16,
                    margin: 5,
                  }}
                >
                  Tipo de Documento
                </div>
                <FormControl fullWidth>
                  <Select
                    value={selectedTipoDocumento.nombre_tipo_documento || ""}
                    label="Tipo Documento"
                    onChange={handleSelectChange}
                    size="small"
                  >
                    {tipo_documento.map((option) => (
                      <MenuItem
                        key={option.id}
                        value={option.nombre_tipo_documento}
                      >
                        {option.nombre_tipo_documento}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
              </div>
              <div style={{ width: "20%", marginRight: 50 }}>
                <div
                  style={{
                    display: "flex",
                    fontSize: 16,
                    margin: 5,
                  }}
                >
                  Numero Documento
                </div>
                <TextField
                  value={selectedNumero}
                  size="small"
                  onChange={handleNumeroChange}
                  fullWidth
                  type="number"
                />
              </div>
            </div>
            <div style={{ width: "20%" }}>
              <div
                style={{
                  display: "flex",
                  fontSize: 16,
                  margin: 5,
                }}
              >
                Fecha de Nacimiento
              </div>
              <LocalizationProvider dateAdapter={AdapterDayjs}>
                <DemoContainer components={["DatePicker"]}>
                  <DatePicker
                    size="small"
                    format="DD/MM/YYYY"
                    value={selectedfecha_nacimiento}
                    onChange={(newValue) => setFechaNacimiento(newValue)}
                  />
                </DemoContainer>
              </LocalizationProvider>
            </div>

            <div
              style={{
                justifyContent: "flex-end",
                overflow: "auto",
                display: "flex",
                textAlign: "left",
              }}
            >
              <Button
                variant="contained"
                color="error"
                onClick={handleClen}
                sx={{marginRight:5}}
              >
                LIMPIAR
              </Button>

              <Button
                variant="contained"
                disabled={
                  !selectedfecha_nacimiento ||
                  !selectedApellido ||
                  !selectedNombre ||
                  !selectedTipoDocumento ||
                  !selectedNumero
                }
                onClick={handleSubmit}
              >
                GUARDAR
              </Button>
            </div>
          </AccordionDetails>
        </Accordion>
      </Box>
    </div>
  );
};
export default Person;
