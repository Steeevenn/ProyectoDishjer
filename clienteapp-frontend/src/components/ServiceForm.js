import React, { useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const ServiceForm = () => {
  const [formData, setFormData] = useState({
    nombreCliente: "",
    nombreContacto: "",
    numero: "",
    direccion: "",
    barrio: "",
    ciudad: "",
    fecha: "",
    horaInicio: "",
    horaFin: "",
    tipoServicio: "",
    tipoContrato: "",
    serialEquipo: "",
    contadorNegro: "",
    contadorColor: "",
    cantidad: "",
    descripcion: "",
    tipoRepuesto: "",
    observaciones: "",
    firmaTecnico: "",
    firmaCliente: "",
  });

  const dateNow = () => {
    // Converti la fecha en un formato YYYY-MM-DD,
    // split divide la fecha en un subarreglo con base en una ocurrencia T y toma el primer valor del arreglo
    var fechaIso = new Date().toISOString().split("T")[0];
    return fechaIso;
  };
  var date = dateNow();
  const timeNow = () => {
    //split divide la fecha en un subarreglo con base en una ocurrencia T y toma el primer segundo del arreglo
    var date = new Date();
    var hours = String(date.getHours()).padStart(2, "0");
    var minutes = String(date.getMinutes()).padStart(2, "0");

    console.log(date);
    return `${hours}:${minutes}`;
  };
  var time = timeNow();

  const isTodaySelected = formData.fecha === date;

  function handleKeyPressNumbers(e) {
    const regex = /[0-9 ]/;
    const specialKeys = [
      "Backspace",
      "ArrowLeft",
      "ArrowRight",
      "Delete",
      "Tab",
      "Home",
      "End",
      "Enter",
    ];
    if (!regex.test(e.key) && !specialKeys.includes(e.key)) {
      e.preventDefault();
    }
  }
  function handleKeyPressWords(e) {
    // condicional para evitar caracteres en nombre
    const regex = /[a-zA-Z \s ]+$/;
    const specialKeys = ["Backspace", "Enter", "Delete", "Tab"];
    if (!regex.test(e.key) && !specialKeys.includes(e.key)) {
      // Evitar que se ingrese el caracter solo letras
      e.preventDefault();
    }
  }

  const combinacionCaracteres = (e) => {
    const regex = /[a-zA-Z0-9\s]/;
    const specialKeys = ["Backspace", "Enter", "Delete", "Tab"];
    if (!regex.test(e.key) && !specialKeys.includes(e.key)) {
      e.preventDefault();
    }
  };

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // Crear cliente
      const clienteResponse = await axios.post(
        "http://localhost:8080/clientes",
        {
          nombreCliente: formData.nombreCliente,
        }
      );
      const clienteId = clienteResponse.data.id;
      console.log("enviando datos cliente ", clienteResponse.data);

      // Crear dirección asociada al cliente
      await axios.post("http://localhost:8080/direcciones", {
        direccion: formData.direccion,
        barrio: formData.barrio,
        ciudad: formData.ciudad,
        clienteId: clienteId,
      });

      // Crear equipo asociado al cliente

      const equipoResponse = await axios.post("http://localhost:8080/equipos", {
        serialEquipo: formData.serialEquipo,
        clienteId: clienteId,
        contadorColor: formData.contadorColor,
        contadorBlancoNegro: formData.contadorNegro,
      });
      const equipoId = equipoResponse.data.id;

      // Crear servicio asociado al equipo
      await axios.post("http://localhost:8080/servicios", {
        fecha: formData.fecha,
        horaInicio: formData.horaInicio,
        horaFin: formData.horaFin,
        tipoServicio: formData.tipoServicio,
        tipoContrato: formData.tipoContrato,
        observaciones: formData.observaciones,
        firmaTecnico: formData.firmaTecnico,
        firmaCliente: formData.firmaCliente,
        equipoId: equipoId,
      });

      // Crear repuesto asociado al equipo
      await axios.post("http://localhost:8080/repuestos", {
        descripcion: formData.descripcion,
        cantidad: formData.cantidad,
        tipo: formData.tipoRepuesto,
        equipoId: equipoId,
      });

      // Crear contacto asociado al cliente
      const contactoResponse = await axios.post(
        "http://localhost:8080/contactos",
        {
          nombreContacto: formData.nombreContacto,
          clienteId: clienteId,
        }
      );
      const contactoId = contactoResponse.data.id;

      // Crear teléfono asociado al contacto
      await axios.post("http://localhost:8080/telefonos", {
        numero: formData.numero,
        contactoId: contactoId,
      });

      const jsonData = {
        id: clienteId,
        nombreCliente: formData.nombreCliente,
        direcciones: [
          {
            direccion: formData.direccion,
            barrio: formData.barrio,
            ciudad: formData.ciudad,
            clienteId: clienteId,
          },
        ],
        equipos: [
          {
            id: equipoId,
            serialEquipo: formData.serialEquipo,
            contadorColor: formData.contadorColor,
            contadorBlancoNegro: formData.contadorNegro,
            servicios: [
              {
                fecha: formData.fecha,
                horaInicio: formData.horaInicio,
                horaFin: formData.horaFin,
                tipoServicio: formData.tipoServicio,
                tipoContrato: formData.tipoContrato,
                observaciones: formData.observaciones,
                firmaTecnico: formData.firmaTecnico,
                firmaCliente: formData.firmaCliente,
                equipoId: equipoId,
              },
            ],
            repuestos: [
              {
                descripcion: formData.descripcion,
                cantidad: formData.cantidad,
                tipo: formData.tipoRepuesto,
                equipoId: equipoId,
              },
            ],
            clienteId: clienteId,
          },
        ],
        contactos: [
          {
            nombreContacto: formData.nombreContacto,
            telefono: {
              numero: formData.numero,
              contactoId: contactoId,
            },
            clienteId: clienteId,
          },
        ],
      };

      // Enviar el JSON al backend para generar el PDF y descargarlo
      const response = await axios.post(
        "http://localhost:8080/generate-pdf",
        jsonData,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement("a");
      link.href = url;
      link.setAttribute("download", "cliente.pdf"); // Nombre del archivo
      document.body.appendChild(link);
      link.click();
      link.remove();

      setFormData({
        nombreCliente: "",
        contacto: "",
        numero: "",
        direccion: "",
        barrio: "",
        ciudad: "",
        fecha: "",
        horaInicio: "",
        horaFin: "",
        tipoServicio: "",
        tipoContrato: "",
        serialEquipo: "",
        contadorNegro: "",
        contadorColor: "",
        cantidad: "",
        descripcion: "",
        tipoRepuesto: "",
        observaciones: "",
        firmaTecnico: "",
        firmaCliente: "",
      });
      console.log("Todos los datos fueron enviados correctamente");
      toast.success("Formulario enviado con exito");
    } catch (error) {
      console.error("Error al crear el servicio:", error);
      console.error("Error generando el pdf ", error);
    }
  };

  const handleClearForm = () => {
    setFormData({
      nombreCliente: "",
      nombreContacto: "",
      numero: "",
      direccion: "",
      barrio: "",
      ciudad: "",
      fecha: "",
      horaInicio: "",
      horaFin: "",
      tipoServicio: "",
      tipoContrato: "",
      serialEquipo: "",
      contadorNegro: "",
      contadorColor: "",
      cantidad: "",
      descripcion: "",
      tipoRepuesto: "",
      observaciones: "",
      firmaTecnico: "",
      firmaCliente: "",
    });
  };
  return (
    <div className="box-wrapper">
      <div className="row">
        <div className="col-md-6 mt-4 ml-1 mb-4 mr-4">
          <div className="container mt-3">
            <nav class="navbar bg-dark navbar-expand-lg bg-body-tertiary custom-navbar-bg ">
              <div div class="container mt-1 d-flex justify-content-start  ">
                <svg
                  version="1.1"
                  xmlns="http://www.w3.org/2000/svg"
                  width="50px"
                  height="45px"
                  viewBox="180 360 280 200"
                >
                  <polygon
                    fill="#68CBE1"
                    points="221.034,578.797 287.405,540.479 221.034,502.159 "
                  ></polygon>
                  <polygon
                    fill="#F5EE25"
                    points="195.985,502.831 262.356,464.513 195.985,426.194 "
                  ></polygon>
                  <polygon
                    fill="#E61C21"
                    points="221.099,425.54 287.47,387.217 221.099,348.898 "
                  ></polygon>
                  <polygon
                    fill="#F02698"
                    points="287.392,463.846 353.763,425.527 287.392,387.208 "
                  ></polygon>
                  <polygon
                    fill="#1E96D2"
                    points="287.392,540.491 353.763,502.173 287.392,463.85 "
                  ></polygon>
                  <g>
                    <polygon
                      fill="#EA80B0"
                      points="353.702,502.19 420.073,463.872 353.702,425.553 	"
                    ></polygon>
                  </g>
                  <polygon
                    fill="#93C93D"
                    points="287.435,463.85 221.064,502.173 287.435,540.491 "
                  ></polygon>
                  <polygon
                    fill="#FA671D"
                    points="287.452,387.2 221.082,425.519 287.452,463.837 "
                  ></polygon>
                  <polygon
                    fill="#700089"
                    points="353.771,425.536 287.401,463.854 353.771,502.173 "
                  ></polygon>
                  <g>
                    <path
                      fill="#231F20"
                      d="M463.637,431.819h22.846c5.153,0,9.081,0.395,11.784,1.183c3.631,1.07,6.742,2.971,9.333,5.701
             		c2.589,2.731,4.56,6.075,5.911,10.03s2.028,8.833,2.028,14.633c0,5.097-0.635,9.488-1.901,13.177
             		c-1.548,4.505-3.758,8.15-6.63,10.938c-2.168,2.111-5.097,3.759-8.784,4.941c-2.759,0.873-6.447,1.309-11.064,1.309h-23.523
             		V431.819z M476.138,442.293V483.3h9.333c3.49,0,6.011-0.197,7.559-0.592c2.028-0.506,3.71-1.364,5.048-2.576
             		c1.337-1.21,2.428-3.202,3.272-5.976c0.845-2.773,1.267-6.552,1.267-11.339c0-4.786-0.422-8.46-1.267-11.022
             		c-0.845-2.562-2.027-4.561-3.547-5.997c-1.521-1.436-3.449-2.407-5.786-2.914c-1.747-0.394-5.167-0.591-10.262-0.591H476.138z"
                    ></path>
                    <path
                      fill="#231F20"
                      d="M584.737,493.73h-13.599l-5.405-14.063h-24.748l-5.11,14.063h-13.26l24.113-61.911h13.219L584.737,493.73z
             		 M561.721,469.236l-8.53-22.974l-8.362,22.974H561.721z"
                    ></path>
                    <path
                      fill="#231F20"
                      d="M601.651,493.73v-51.438h-18.37v-10.474h49.199v10.474h-18.328v51.438H601.651z"
                    ></path>
                    <path
                      fill="#231F20"
                      d="M692.726,493.73h-13.599l-5.405-14.063h-24.748l-5.11,14.063h-13.261l24.114-61.911h13.219L692.726,493.73z
             		 M669.709,469.236l-8.531-22.974l-8.361,22.974H669.709z"
                    ></path>
                    <path
                      fill="#231F20"
                      d="M701.744,493.73v-61.911h12.499v61.911H701.744z"
                    ></path>
                    <path
                      fill="#231F20"
                      d="M768.534,470.968l12.121,3.842c-1.858,6.758-4.948,11.776-9.27,15.056c-4.322,3.28-9.806,4.92-16.45,4.92
             		c-8.221,0-14.978-2.809-20.271-8.426c-5.294-5.615-7.939-13.295-7.939-23.036c0-10.304,2.66-18.308,7.981-24.009
             		s12.317-8.552,20.988-8.552c7.574,0,13.726,2.238,18.456,6.715c2.815,2.647,4.927,6.447,6.335,11.403l-12.374,2.956
             		c-0.732-3.209-2.26-5.743-4.582-7.601c-2.323-1.859-5.146-2.788-8.468-2.788c-4.589,0-8.313,1.647-11.17,4.941
             		c-2.857,3.294-4.287,8.63-4.287,16.006c0,7.827,1.408,13.402,4.224,16.724c2.814,3.322,6.476,4.982,10.98,4.982
             		c3.322,0,6.18-1.056,8.573-3.166C765.775,478.823,767.492,475.502,768.534,470.968z"
                    ></path>
                    <path
                      fill="#231F20"
                      d="M791.614,463.155c0-6.306,0.943-11.599,2.829-15.878c1.408-3.153,3.329-5.982,5.766-8.489
             		c2.435-2.505,5.102-4.364,8.003-5.575c3.855-1.632,8.305-2.449,13.345-2.449c9.122,0,16.42,2.829,21.897,8.488
             		c5.476,5.66,8.214,13.529,8.214,23.607c0,9.995-2.717,17.815-8.15,23.46c-5.435,5.646-12.698,8.467-21.792,8.467
             		c-9.206,0-16.527-2.809-21.961-8.426C794.331,480.745,791.614,473.01,791.614,463.155z M804.495,462.733
             		c0,7.011,1.618,12.325,4.856,15.942c3.238,3.618,7.349,5.427,12.332,5.427c4.982,0,9.072-1.795,12.269-5.385
             		c3.194-3.589,4.792-8.973,4.792-16.153c0-7.094-1.556-12.387-4.666-15.878c-3.111-3.491-7.243-5.237-12.395-5.237
             		c-5.152,0-9.306,1.767-12.459,5.3C806.071,450.282,804.495,455.61,804.495,462.733z"
                    ></path>
                  </g>
                </svg>
                <h4 className="text-white-custom  ">
                  Inicio de Servicio Técnico
                </h4>
                <Link
                  to="/clientes"
                  className="btn btn-link ml-xl-5 my-2 my-sm-4"
                >
                  Clientes
                </Link>
              </div>
              <form className="form-inline ml-xl-5 "></form>
            </nav>
          </div>
          <form onSubmit={handleSubmit} className="mt-5 container left ">
            <div className="form-group">
              <label>Nombre Cliente</label>
              <input
                type="text"
                className="form-control"
                name="nombreCliente"
                value={formData.nombreCliente}
                onChange={handleChange}
                required
                onKeyDown={(e) => handleKeyPressWords(e)}
              />
            </div>

            <div className="form-group">
              <label>Nombre Contacto</label>
              <input
                type="text"
                className="form-control"
                name="nombreContacto"
                value={formData.nombreContacto}
                onChange={handleChange}
                required
                onKeyDown={(e) => handleKeyPressWords(e)}
              />
            </div>
            <div className="form-group">
              <label>Telefono</label>
              <input
                type="text"
                className="form-control"
                name="numero"
                value={formData.numero}
                onChange={handleChange}
                required
                onKeyDown={(e) => handleKeyPressNumbers(e)}
              />
            </div>
            <div className="form-group">
              <label>Dirección</label>
              <input
                type="text"
                className="form-control"
                name="direccion"
                value={formData.direccion}
                onChange={handleChange}
                required
              />
            </div>

            <div className="form-group">
              <label>Barrio</label>
              <input
                type="text"
                className="form-control"
                name="barrio"
                value={formData.barrio}
                onChange={handleChange}
                required
                onKeyDown={(e) => handleKeyPressWords(e)}
              />
            </div>
            <div className="form-group">
              <label>Ciudad</label>
              <input
                type="text"
                className="form-control"
                name="ciudad"
                value={formData.ciudad}
                onChange={handleChange}
                required
                onKeyDown={(e) => handleKeyPressWords(e)}
              />
            </div>

            <div className="form-group">
              <label>Fecha</label>
              <input
                type="date"
                className="form-control"
                name="fecha"
                value={formData.fecha}
                onChange={handleChange}
                required
                min={date}
              />
            </div>
            <div className="form-group">
              <label>Hora de Inicio</label>
              <input
                type="time"
                className="form-control"
                name="horaInicio"
                value={formData.horaInicio}
                onChange={handleChange}
                required
                min={isTodaySelected ? time : ""}
              />
            </div>
            <div className="form-group">
              <label>Hora de Fin</label>
              <input
                type="time"
                className="form-control"
                name="horaFin"
                value={formData.horaFin}
                onChange={handleChange}
                required
              />
            </div>
            <div className="form-group">
              <label>Tipo de Servicio</label>
              <select
                className="form-control"
                name="tipoServicio"
                value={formData.tipoServicio}
                onChange={handleChange}
                required
              >
                <option value="">Seleccione un tipo</option>
                <option value="mantenimiento preventivo">
                  Mantenimiento Preventivo
                </option>
                <option value="mantenimiento correctivo">
                  Mantenimiento Correctivo
                </option>
                <option value="reparación">Reparación</option>
              </select>
            </div>
            <div className="form-group">
              <label>Tipo de Contrato</label>
              <select
                className="form-control"
                name="tipoContrato"
                value={formData.tipoContrato}
                onChange={handleChange}
                required
              >
                <option value="">Seleccione un tipo</option>
                <option value="contrato">Contrato</option>
                <option value="garantia">Garantia</option>
                <option value="facturar">Facturar</option>
              </select>
            </div>

            <div className="form-group">
              <label>Serial del Equipo</label>
              <input
                type="text"
                className="form-control"
                name="serialEquipo"
                value={formData.serialEquipo}
                onChange={handleChange}
                required
                onKeyDown={(e) => combinacionCaracteres(e)}
              />
            </div>
            <div className="form-group">
              <label>Contador Negro</label>
              <input
                type="number"
                className="form-control"
                name="contadorNegro"
                value={formData.contadorNegro}
                onChange={handleChange}
                required
                onKeyDown={(e) => handleKeyPressNumbers(e)}
              />
            </div>
            <div className="form-group">
              <label>Contador Color</label>
              <input
                type="number"
                className="form-control"
                name="contadorColor"
                value={formData.contadorColor}
                onChange={handleChange}
                onKeyDown={(e) => handleKeyPressNumbers(e)}
              />
            </div>
            <div className="form-group">
              <label>Repuesto</label>
              <input
                type="text"
                className="form-control"
                name="descripcion"
                value={formData.descripcion}
                onChange={handleChange}
                required
                onKeyDown={(e) => handleKeyPressWords(e)}
              />
            </div>
            <div className="form-group">
              <label>Cantidad Repuesto</label>
              <input
                type="number"
                className="form-control"
                name="cantidad"
                value={formData.cantidad}
                onChange={handleChange}
                required
                onKeyDown={(e) => handleKeyPressNumbers(e)}
              />
            </div>
            <div className="form-group">
              <label>Estado de repuesto</label>
              <select
                className="form-control"
                name="tipoRepuesto"
                value={formData.tipoRepuesto}
                onChange={handleChange}
                required
              >
                <option value="">Seleccione un tipo</option>
                <option value="cotizacion">Cotizacion</option>
                <option value="cambio">Cambio</option>
              </select>
            </div>
            <div className="form-group">
              <label>Observaciones</label>
              <textarea
                className="form-control"
                name="observaciones"
                value={formData.observaciones}
                onChange={handleChange}
                required
                maxLength="243"
              />
            </div>
            <div className="form-group">
              <label>Firma Técnico</label>
              <input
                type="text"
                className="form-control"
                name="firmaTecnico"
                value={formData.firmaTecnico}
                onChange={handleChange}
                required
              />
            </div>
            <div className="form-group">
              <label>Firma Cliente</label>
              <input
                type="text"
                className="form-control"
                name="firmaCliente"
                value={formData.firmaCliente}
                onChange={handleChange}
                required
              />
            </div>
            <div style={{ display: "flex", justifyContent: "space-around" }}>
              <button
                type="submit"
                className="btn  btn-dark btn-primary "
                style={{ marginTop: "10px" }}
              >
                Enviar
              </button>
              <button
                type="button"
                className="btn btn-dark btn-primary"
                style={{ marginTop: "10px" }}
                onClick={handleClearForm}
              >
                Limpiar
              </button>
            </div>
          </form>
          <ToastContainer
            position="bottom-center"
            autoClose={5000}
            hideProgressBar={false}
            newestOnTop={false}
            closeOnClick
            rtl={false}
            pauseOnFocusLoss
            draggable
            pauseOnHover
            theme="dark"
          />
        </div>
      </div>
    </div>
  );
};
export default ServiceForm;
