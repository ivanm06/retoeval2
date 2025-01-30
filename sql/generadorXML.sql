use jaiskibel;
SET SESSION group_concat_max_len = 1000000; -- Ajusta el tamaño según sea necesario
SELECT 
CONCAT(
		'<?xml version="1.0" encoding="UTF-8"?>',
        '<?xml-stylesheet href="./xsl/articulos.xsl" type="text/xsl"?>',
        '<articulos xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="articulos.xsd">',
        GROUP_CONCAT(
    CONCAT(
        '<articulo>',
            '<id>', a.id, '</id>',
            '<tipo>', 
            CASE 
                WHEN s.idArticulo IS NOT NULL THEN 'ski'
                WHEN sb.idArticulo IS NOT NULL THEN 'snowboard'
                WHEN acc.idArticulo IS NOT NULL THEN 'accesorio'
            END, 
            '</tipo>',
            '<nombre>', a.nombre, '</nombre>',
            '<descripcion>', a.descripcion, '</descripcion>',
            '<talla>', a.talla, '</talla>',
            '<precio>', a.precio, '</precio>',
            '<establecimiento>', ae.idEstablecimiento , '</establecimiento>',
            IF(s.idArticulo IS NOT NULL OR sb.idArticulo IS NOT NULL, 
                CONCAT(
                    '<nivel>', s.nivel, '</nivel>',
                    '<modalidad>', COALESCE(s.modalidad, ''), '</modalidad>'
                ), 
                ''
            ),
            IF(sb.idArticulo IS NOT NULL, 
                CONCAT(
                    '<modalidad>', sb.modalidad, '</modalidad>'
                ), 
                ''
            ),
            IF(acc.idArticulo IS NOT NULL, 
                CONCAT(
                    '<tipoAccesorio>', COALESCE(acc.tipo, ''), '</tipoAccesorio>'
                ), 
                ''
            ),
            '<stock>', ae.cantidad, '</stock>',
        '</articulo>'
    )
    ), '</articulos>'
    ) AS articulos_xml
FROM 
    Establecimiento e
JOIN 
    articuloEstablecimiento ae ON e.id = ae.idEstablecimiento
JOIN 
    Articulo a ON ae.idArticulo = a.id
LEFT JOIN 
    Ski s ON a.id = s.idArticulo
LEFT JOIN 
    Snowboard sb ON a.id = sb.idArticulo
LEFT JOIN 
    Accesorios acc ON a.id = acc.idArticulo;