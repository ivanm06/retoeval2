// Sacar el id del establecimiento de los parámetros de la url.
const urlParams = new URLSearchParams(window.location.search);
const establecimiento = urlParams.get('e') ?? 0;

// Filtrar al cargar la página.
document.addEventListener("DOMContentLoaded", function () {
    // Cuando se cambia el valor de  un filtro, se filtra otra vez. 
    const filtros = document.querySelectorAll(".filtros input[type='checkbox']");
    filtros.forEach(filtro => filtro.addEventListener("change", aplicarFiltros));

    // Filtrar artículos en base a su tipo.
    function aplicarFiltros() {
        const ski = document.getElementById("ski").checked;
        const snow = document.getElementById("snow").checked;
        const accesorios = document.getElementById("acc").checked;

        console.log(ski, snow, accesorios);

        // Verificar si el artículo cumple los filtros por cada artículo.
        document.querySelectorAll(".articulo").forEach(articulo => {
            const tipo = articulo.getAttribute("data-tipo");
            const e = articulo.getAttribute("data-e");

            if (((tipo === "ski" && ski) || (tipo === "snowboard" && snow) || (tipo === "accesorio" && accesorios)) && e == establecimiento) articulo.style.display = "flex";
            else articulo.style.display = "none";
        });
    }

    // Aplicar filtros al cargar la página.
    aplicarFiltros();
});