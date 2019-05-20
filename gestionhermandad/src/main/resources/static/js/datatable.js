$(document).ready(function() {
    $('#hermanosTabla').DataTable( {
        "language": {
            "url": "//cdn.datatables.net/plug-ins/1.10.19/i18n/Spanish.json"
        },
        responsive: true,
         "info": false,
         "iDisplayLength": 5,
         "aLengthMenu": [[5, 10, 50, -1], [5, 10, 50, "Todos"]]
    } );
} );