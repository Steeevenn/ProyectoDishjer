// MiComponente.jsx

import React from 'react';
import './Stylos.css'; // Importa los estilos CSS

function MiComponente() {
  return (
    <div class="ui secondary inverted pointing menu">
  <a data-testid="qa.header/sales-tab.a" class="active item">Ventas</a>
  <a class="item">Compras</a>
  <a data-testid="qa.header/cartera-tab.a" class="item"><div><div><div>Cartera</div></div></div></a>
  <a data-testid="qa.header/accounting-tab.a" class="item"><div>Contabilidad</div></a>
  <a data-testid="qa.header/payroll-tab.a" class="item"><div>Nómina</div></a>
  <a class="item">Terceros</a>
  <a data-testid="qa.header/products-tab.a" class="item">Productos e Inventario</a>
</div>

  );
}

export default MiComponente;
