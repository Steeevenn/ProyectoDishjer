import axios from "axios";
import { useForm } from "react-hook-form";
import "bootstrap/dist/css/bootstrap.min.css";
import "../App.css";
import { Link } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const ServiceForm = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm({
    defaultValues: {
      nombreCliente: "",
      telefono: "",
      direccion: "",
      fechaServicio: "",
      tipoServicio: "",
      observaciones: "",
    },
  });

  const onSubmit = async (data) => {
    try {
      // Datos del cliente
      const clienteData = {
        nombreCliente: data.nombreCliente,
        telefono: data.telefono,
        direccion: data.direccion,
      };
      const clienteResponse = await axios.post(
        "http://localhost:8080/clientesService",
        clienteData
      );
      console.log("Datos del cliente enviados con éxito", clienteResponse.data);
      const clienteId = clienteResponse.data.id;

      const solicitudData = {
        tipoServicio: data.tipoServicio,
        observaciones: data.observaciones,
        fechaServicio: data.fechaServicio,
        clienteServiceId: clienteId,
      };

      const solicitudResponse = await axios.post(
        "http://localhost:8080/solicitudesServicio",
        solicitudData
      );
      console.log("formulario enviado con exito", solicitudResponse.data);

      toast.success("Formulario enviado con exito");
      reset();
    } catch (error) {
      console.log("error al enviar los datos", error);
    }
  };
  const dateNow = () => {
    // Converti la fecha en un formato YYYY-MM-DD,
    // split divide la fecha en un subarreglo con base en una ocurrencia T y toma el primer valor del arreglo
    let fechaIso = new Date().toISOString().split("T")[0];
    return fechaIso;
  };

  return (
    <div className="box-wrapper ">
      <div className="row">
        <div className="col-md-5">
          <div className="container mt-3 ml-3 col-md-10">
            <nav className="navbar bg-dark navbar-expand-lg bg-body-tertiary custom-navbar-bg ">
              <div className="container mt-3 d-flex justify-content-start ">
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
                <h4 className="navbar-brand text-white-custom " href="#">
                  Solicitud Servicio Tecnico DISHJER
                </h4>
              </div>
            </nav>
          </div>
          <div className="container ml-3 mt-3 left col-md-10">
            <form onSubmit={handleSubmit(onSubmit)} className="container mt-3 ">
              <div className="form-group ">
                <label>Nombre</label>
                <input
                  className={`form-control ${
                    errors.nombreCliente ? "is-invalid" : ""
                  }`}
                  {...register("nombreCliente", { required: true })}
                />
                {errors.nombreCliente && (
                  <div className="invalid-feedback">
                    Este campo es obligatorio
                  </div>
                )}
              </div>
              <div className="form-group">
                <label>Teléfono</label>
                <input
                  className={`form-control ${
                    errors.telefono ? "is-invalid" : ""
                  }`}
                  {...register("telefono", { required: true })}
                />
                {errors.telefono && (
                  <div className="invalid-feedback">
                    Este campo es obligatorio
                  </div>
                )}
              </div>
              <div className="form-group">
                <div className="form-group">
                  <label>Fecha Solicitud</label>
                  <input
                    className={`form-control ${
                      errors.fechaServicio ? "is-invalid" : ""
                    }`}
                    {...register("fechaServicio", { required: true })}
                    type="date"
                    min={dateNow()}
                  />
                  {errors.fechaServicio && (
                    <div className="invalid-feedback">
                      Este campo es obligatorio
                    </div>
                  )}
                </div>
                <label>Dirección</label>
                <input
                  className={`form-control ${
                    errors.direccion ? "is-invalid" : ""
                  }`}
                  {...register("direccion", { required: true })}
                />
                {errors.direccion && (
                  <div className="invalid-feedback">
                    Este campo es obligatorio
                  </div>
                )}
              </div>
              <div className="form-group">
                <label>Tipo de Servicio</label>
                <select
                  className={`form-control ${
                    errors.tipoServicio ? "is-invalid" : ""
                  }`}
                  {...register("tipoServicio", { required: true })}
                >
                  <option value="">Selecciona un tipo de servicio</option>
                  <option value="mantenimiento">
                    Mantenimiento Preventivo
                  </option>
                  <option value="reparacion">Reparación</option>
                  <option value="instalacion">Garantia</option>
                  <option value="otro">Otro</option>
                </select>
                {errors.tipoServicio && (
                  <div className="invalid-feedback">
                    Este campo es obligatorio
                  </div>
                )}
              </div>
              <div className="form-group">
                <label>Observaciones</label>
                <textarea
                  className="form-control"
                  {...register("observaciones")}
                />
              </div>

              <button
                type="submit"
                className="btn btn-dark btn-primary mt-3  mb-3"
              >
                Enviar
              </button>
              <Link
                to="/clientes"
                className="btn btn-link ml-xl-5 my-2 my-sm-4"
              >
                Clientes
              </Link>
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
        <div className="col-md-6">
          <div className="container mt-5 mr-5 ">
            <img
              src="/printer.jpg"
              alt="Printer"
              className="img-fluid mt-3 d-flex mb-3 mr-5 "
            />
          </div>
        </div>
        <div className="container-fluid ml-3  mt-3 derechos">
          <p>© 2024 DISHJER. Todos los derechos reservados. </p>
        </div>
      </div>
    </div>
  );
};

export default ServiceForm;
