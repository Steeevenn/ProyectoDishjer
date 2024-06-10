import React, { useState } from 'react';
import axios from 'axios';

const ServiceForm = () => {
    const [formData, setFormData] = useState({
        nombreCliente: '',
        nombreContacto: '',
        numero: '',
        direccion: '',
        barrio: '',
        ciudad: '',
        fecha: '',
        horaInicio: '',
        horaFin: '',
        tipoServicio: '',
        tipoContrato: '',
        serialEquipo: '',
        contadorNegro: '',
        contadorColor: '',
        cantidad: '',
        descripcion: '',
        tipoRepuesto: '', // Ajustado para coincidir con la entidad Repuesto
        observaciones: '',
        firmaTecnico: '',
        firmaCliente: '',
    });
    

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };


    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            // Crear cliente
            const clienteResponse = await axios.post('http://localhost:8080/clientes', {
                nombreCliente: formData.nombreCliente,
            });
            const clienteId = clienteResponse.data.id;
            console.log("enviando datos cliente ", clienteResponse.data);
    
            // Crear dirección asociada al cliente
            await axios.post('http://localhost:8080/direcciones', {
                direccion: formData.direccion,
                barrio: formData.barrio,
                ciudad: formData.ciudad,
                cliente: { id: clienteId }
            });
    
            // Crear equipo asociado al cliente
            const equipoResponse = await axios.post('http://localhost:8080/equipos', {
                serialEquipo: formData.serialEquipo,
                cliente: { id: clienteId },
                contadorColor: formData.contadorColor,
                contadorBlancoNegro: formData.contadorNegro
            });
            const equipoId = equipoResponse.data.id;
    
            // Crear servicio asociado al equipo
            await axios.post('http://localhost:8080/servicios', {
                fecha: formData.fecha,
                horaInicio: formData.horaInicio,
                horaFin: formData.horaFin,
                tipoServicio: formData.tipoServicio,
                tipoContrato: formData.tipoContrato,
                observaciones: formData.observaciones,
                firmaTecnico: formData.firmaTecnico,
                firmaCliente: formData.firmaCliente,
                equipo: { id: equipoId }
            });
    
            // Crear repuesto asociado al equipo
            await axios.post('http://localhost:8080/repuestos', {
                descripcion: formData.descripcion,
                cantidad: formData.cantidad,
                tipo: formData.tipoRepuesto,
                equipo: { id: equipoId }
            });
    
            // Crear contacto asociado al cliente
            const contactoResponse = await axios.post('http://localhost:8080/contactos', {
                nombreContacto: formData.nombreContacto,
                cliente: { id: clienteId }
            });
            const contactoId = contactoResponse.data.id;
    
            // Crear teléfono asociado al contacto
            await axios.post('http://localhost:8080/telefonos', {
                numero: formData.numero,
                contacto: { id: contactoId }
            });
            setFormData({
                nombreCliente: '',
                contacto: '',
                numero: '',
                direccion: '',
                barrio: '',
                ciudad: '',
                fecha: '',
                horaInicio: '',
                horaFin: '',
                tipoServicio: '',
                tipoContrato: '',
                serialEquipo: '',
                contadorNegro: '',
                contadorColor: '',
                cantidad: '',
                descripcion: '',
                tipoRepuesto: '',
                observaciones: '',
                firmaTecnico: '',
                firmaCliente: '',
            });
            console.log('Todos los datos fueron enviados correctamente');
            alert('El formulario se envió correctamente.');
        } catch (error) {
            console.error('Error al crear el servicio:', error);
        }
    

    };

    const handleClearForm = () => {
        setFormData({
            nombreCliente: '',
            nombreContacto: '',
            numero: '',
            direccion: '',
            barrio: '',
            ciudad: '',
            fecha: '',
            horaInicio: '',
            horaFin: '',
            tipoServicio: '',
            tipoContrato: '',
            serialEquipo: '',
            contadorNegro: '',
            contadorColor: '',
            cantidad: '',
            descripcion: '',
            tipoRepuesto: '',
            observaciones: '',
            firmaTecnico: '',
            firmaCliente: '',
        });
    };
    return (
        <div className="container mt-5">
            <h2>Inicio de Servicio Técnico</h2>
            <form onSubmit={handleSubmit} >
                <div className="form-group">
                    <label>Nombre Cliente</label>
                    <input
                        type="text"
                        className="form-control"
                        name="nombreCliente"
                        value={formData.nombreCliente}
                        onChange={handleChange}
                        required
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
                        <option value="mantenimiento preventivo">Mantenimiento Preventivo</option>
                        <option value="mantenimiento correctivo">Mantenimiento Correctivo</option>
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
                        type="text" // Cambiado de number a text
                        className="form-control"
                        name="serialEquipo"
                        value={formData.serialEquipo}
                        onChange={handleChange}
                        required
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
                <div style={{ display: 'flex', justifyContent: 'space-between' }}>
    <button type="submit" className="btn btn-primary"style={{ marginTop: '10px' }}>Enviar</button>
    <button type="button" className="btn btn-primary"style={{ marginTop: '10px' }} onClick={handleClearForm}>Limpiar</button>
</div>
            </form>
        </div>
        
    );
};
export default ServiceForm;

