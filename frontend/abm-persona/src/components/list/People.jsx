import React from "react";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import config from "../../config.json";
import _ from "lodash";
import axios from "axios";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import { styled } from "@mui/material/styles";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TablePagination from "@mui/material/TablePagination";
import TableRow from "@mui/material/TableRow";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import "./People.css";
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import Accordion from "@mui/material/Accordion";
import AccordionDetails from "@mui/material/AccordionDetails";
import AccordionSummary from "@mui/material/AccordionSummary";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import AddIcon from "@mui/icons-material/Add";
import { grey } from "@mui/material/colors";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import IconButton from "@mui/material/IconButton";
import Tooltip from "@mui/material/Tooltip";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import useMediaQuery from "@mui/material/useMediaQuery";
import { useTheme } from "@mui/material/styles";

const greyheader = grey[600];

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: greyheader,
    color: theme.palette.common.white,
    textAlign: "center"
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
    textAlign: "center"
  },
}));

const style = {
  row : {
		textAlign: "center"
  }
}

const People = () => {
  const [page, setPage] = useState(0);
  const navigate = useNavigate();
  const [rowsPerPage, setRowsPerPage] = useState(10);
  const [count, setCount] = useState(0);
  const [expanded, setExpanded] = useState(false);
  const [persons, setPersons] = useState([]);
  const [tipo_documento, setTipoDocumento] = useState([]);
  const [selectedNombre, setselectedNombre] = useState("");
  const [selectedTipoDocumento, setselectedTipoDocumento] = useState({});
  const [open, setOpen] = useState(false);
  const [persondelete, setpersondelete] = useState({});
  const theme = useTheme();
  const fullScreen = useMediaQuery(theme.breakpoints.down("md"));

  const fetchPersons = async (page, rowsPerPage) => {
    let url = `${config.apiUrlPerson}search?page=${page || 0}&size=${rowsPerPage || 10}`;
    if (
      selectedNombre &&
      selectedTipoDocumento &&
      !_.isEmpty(selectedTipoDocumento)
    ) {
      url += `&nombre=${selectedNombre}&id_tipoDocumento=${selectedTipoDocumento.id}`;
    } else if (selectedNombre) {
      url += `&nombre=${selectedNombre}`;
    } else if (selectedTipoDocumento && !_.isEmpty(selectedTipoDocumento)) {
      url += `&id_tipoDocumento=${selectedTipoDocumento.id}`;
    }
    const res = await axios.get(url);
    setPersons(res.data.items);
    setRowsPerPage(res.data.size);
    setCount(res.data.total);
    setPage(res.data.page);
  };

  const fetchTipoDocumento = async () => {
    const res = await axios.get(`${config.apiUrlDocumento}get-tipo-documento`);
    setTipoDocumento(res.data);
  };

  useEffect(() => {
    const fetchData = async () => {
      await fetchPersons();
      await fetchTipoDocumento();
    };
    fetchData();
  }, []);

  const handleChange = (panel) => (event, isExpanded) => {
    setExpanded(isExpanded ? panel : false);
  };

  const handleDelete = async (e) => {
    e.preventDefault();
    await axios.delete(`${config.apiUrlPerson}delete/${persondelete.id}`);
    setpersondelete({});
    fetchPersons();
    setOpen(false);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    fetchPersons(page, event.target.value);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
    fetchPersons(newPage, rowsPerPage);
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

  const handleClickOpen = (item) => {
    setpersondelete(item);
    setOpen(true);
  };

  const handleClose = () => {
    setpersondelete({});
    setOpen(false);
  };

  const handlefind = () => {
    fetchPersons();
  };

  const handleclean = () => {
    setselectedTipoDocumento({});
    setselectedNombre("");
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
              Personas
            </Typography>
            <Button
              variant="contained"
              onClick={() => navigate("/person/new")}
              color="success"
              startIcon={<AddIcon />}
            >
              Nuevo
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
              sx={{ width: "33%", flexShrink: 0, color: "#000", fontSize: 20 }}
            >
              Filtros
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

              <div style={{ width: "20%" }}>
                <div
                  style={{
                    display: "flex",
                    fontSize: 16,
                    margin: 5,
                  }}
                >
                  Tipo Documento
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
            </div>

            <div
              style={{
                justifyContent: "flex-end",
                overflow: "auto",
                display: "flex",
                textAlign: "left",
              }}
            >
              <Button variant="contained" sx={{marginRight:5}} onClick={handleclean}>
                Limpiar
              </Button>
              <Button variant="contained" color="success" onClick={handlefind}>
                Buscar
              </Button>
            </div>
          </AccordionDetails>
        </Accordion>
      </Box>

      <Box sx={{ margin: "20px", backgroundColor: "#f5f5f5" }}>
        <div>
          <TableContainer>
            <Table stickyHeader aria-label="sticky table">
              <TableHead>
                <TableRow>
                  <StyledTableCell>Id</StyledTableCell>
                  <StyledTableCell>Nombre</StyledTableCell>
                  <StyledTableCell>Apellido</StyledTableCell>
                  <StyledTableCell>Numero de Documento</StyledTableCell>
                  <StyledTableCell>Fecha Nacimiento</StyledTableCell>
                  <StyledTableCell>Tipo Documento</StyledTableCell>
                  <StyledTableCell>Acciones</StyledTableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {persons.map((item) => {
                  return (
                    <TableRow key={item.id}>
                      <StyledTableCell>{item.id}</StyledTableCell>
                      <StyledTableCell>{item.nombre}</StyledTableCell>
                      <StyledTableCell>{item.apellido}</StyledTableCell>
                      <StyledTableCell>{item.numero_documento}</StyledTableCell>
                      <StyledTableCell>{item.fecha_nacimiento}</StyledTableCell>
                      <StyledTableCell>
                        {item.tipo_documento.nombre_tipo_documento}
                      </StyledTableCell>
                      <StyledTableCell>
                        <div>
                          <Tooltip
                            title={"Editar"}
                            position="top"
                            trigger="mouseenter focus"
                          >
                            <IconButton
                              onClick={() => navigate(`/person/${item.id}`)}
                            >
                              <EditIcon />
                            </IconButton>
                          </Tooltip>
                          <Tooltip
                            title={"Eliminar"}
                            position="top"
                            trigger="mouseenter focus"
                          >
                            <IconButton onClick={() => handleClickOpen(item)}>
                              <DeleteIcon />
                            </IconButton>
                          </Tooltip>
                        </div>
                      </StyledTableCell>
                    </TableRow>
                  );
                })}
              </TableBody>
            </Table>
          </TableContainer>
          <TablePagination
            rowsPerPageOptions={[10, 25, 100]}
            component="div"
            count={count}
            rowsPerPage={rowsPerPage}
            page={page}
            onPageChange={handleChangePage}
            onRowsPerPageChange={handleChangeRowsPerPage}
          />
        </div>
      </Box>
      <Dialog
        fullScreen={fullScreen}
        open={open}
        onClose={handleClose}
        aria-labelledby="responsive-dialog-title"
      >
        <DialogTitle id="responsive-dialog-title">{"Eliminar"}</DialogTitle>
        <DialogContent>
          <DialogContentText>
            {`Desea elimniar a ${persondelete.nombre} ${persondelete.apellido} de la lista?`}
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button autoFocus onClick={handleClose}>
            Cancelar
          </Button>
          <Button onClick={handleDelete} autoFocus>
            Eliminar
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default People;
