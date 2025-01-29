<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/">
        <html lang="es">
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <link rel="stylesheet" href="index.css"/>
                <link rel="stylesheet" href="globals.css"/>
                <link rel="stylesheet" href="equipamiento.css"/>
                <title>Equipamiento</title>
                <script src="https://unpkg.com/@tailwindcss/browser@4"></script>
            </head>
            <body>
                <header class="w-screen text-white flex flex-row justify-between items-center align-center p-6 bg-black relative">
                    <a href="/" class="text-[1.4rem] md:text-[2rem]" style='font-family: "Nosifer", serif;'>Jai<span class="text-blue-200">SKI</span>bel</a>
                    <label class="md:hidden" for="menu"><svg class="w-10 h-auto" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M3 12h18"/><path d="M3 18h18"/><path d="M3 6h18"/></svg></label>
                    <input class="hidden" type="checkbox" name="menu" id="menu"/>
                    <nav class="dp hidden absolute top-[99%] bg-[#111] p-2 px-4 z-[5] right-0 min-w-[60vw] justify-center rounded-b-md text-md">
                        <ul class="flex flex-col gap-4">
                            <li><a href="/index.html#aboutme">Nosotros</a></li>
                            <li><a href="/establecimiento.xml">Establecimientos</a></li>
                            <li><a href="/contacto.html">Contacto</a></li>
                            <li><a href="/reserva.html">Reservar</a></li>
                        </ul>
                    </nav>
                    <nav class="hidden md:block">
                        <ul class="gap-[2rem]">
                            <li><a href="/index.html#aboutme">Nosotros</a></li>
                            <li><a href="/establecimiento.xml">Establecimientos</a></li>
                            <li><a href="/contacto.html">Contacto</a></li>
                            <li><a href="/reserva.html">Reservar</a></li>
                        </ul>
                    </nav>
                </header>

                <main class="flex flex-row">
                    <section id="articulos" class="grow flex flex-row flex-wrap gap-10 justify-center items-center p-10 pt-20 pb-40 items-start">
                        <xsl:for-each select="//articulo">
                            <div class="articulo w-[40vh] h-fit bg-[#eee] min-h-100 rounded-md shadow-md hover:scale-105 cursor-pointer flex flex-col" data-tipo="{tipo}" data-e="{establecimiento}">
                                <div class="relative">
                                    <img class="rounded-md" src="{imagen}" alt="establecimiento"/>
                                    <span class="absolute top-1 right-1 px-2 bg-black/20 rounded-md">
                                        <xsl:choose>
                                            <xsl:when test="stock &gt; 0"><xsl:value-of select="stock"/> en stock</xsl:when>
                                            <xsl:otherwise>Fuera de stock</xsl:otherwise>
                                        </xsl:choose>
                                    </span>
                                </div>
                                <div class="p-4 flex-grow flex">
                                    <div class="flex flex-col gap-2 justify-between h-auto flex-grow">
                                        <h2 class="text-xl font-semibold text-center"><xsl:value-of select="nombre"/></h2>
                                        <p class="text-gray-700"><xsl:value-of select="descripcion"/></p>
                                        <div class="flex w-full justify-between">
                                            <p><xsl:value-of select="talla"/></p>
                                            <p><xsl:value-of select="precio"/>€/dia</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </xsl:for-each>
                    </section>

                    <!-- Filtros -->
                    <div class="filtros select-none fixed bottom-0 flex justify-between bg-[#222] shadow-lg shadow-[#ffffff30] gap-4 text-white p-4 px-10 rounded-full left-1/2 transform -translate-x-1/2 -translate-y-1/2">
                        <div class="flex gap-2"><input checked="true" id="ski" type="checkbox"/><label for="ski">Ski</label></div>
                        <div class="flex gap-2"><input checked="true" id="snow" type="checkbox"/><label for="snow">Snowboard</label></div>
                        <div class="flex gap-2"><input checked="true" id="acc" type="checkbox" /><label for="acc">Accesorios</label></div>
                    </div>
                    <!-- -->
                </main>
                <footer class="p-4 pt-8 flex flex-col h-fit items-center bg-black text-white">
                    <section class="flex justify-between w-full">
                        <div class="flex flex-col justify-between">
                            <a href="/establecimiento.xml" class="hover:text-gray-500">Establecimientos</a>
                            <a href="/contacto.html" class="hover:text-gray-500">Contacto</a>
                            <a href="/index.html#aboutme" class="hover:text-gray-500">Sobre Nosotros</a>
                        </div>
                        <div class="flex items-center gap-4">
                            <a href="https://1dam3.es"><svg class="w-10 h-auto hover:text-gray-500 cursor-pointer" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" ><path d="M22 4s-.7 2.1-2 3.4c1.6 10-9.4 17.3-18 11.6 2.2.1 4.4-.6 6-2C3 15.5.5 9.6 3 5c2.2 2.6 5.6 4.1 9 4-.9-4.2 4-6.6 7-3.8 1.1 0 3-1.2 3-1.2z"/></svg></a>
                            <a href="https://1dam3.es"><svg class="w-10 h-auto hover:text-gray-500 cursor-pointer" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" ><rect width="20" height="20" x="2" y="2" rx="5" ry="5"/><path d="M16 11.37A4 4 0 1 1 12.63 8 4 4 0 0 1 16 11.37z"/><line x1="17.5" x2="17.51" y1="6.5" y2="6.5"/></svg></a>
                            <a href="https://1dam3.es"><svg class="w-10 h-auto hover:text-gray-500 cursor-pointer" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" ><path d="M18 2h-3a5 5 0 0 0-5 5v3H7v4h3v8h4v-8h3l1-4h-4V7a1 1 0 0 1 1-1h3z"/></svg></a>
                        </div>
                    </section>
                    <span>© 2025 JaiSKIbel. Todos los derechos reservados.</span>
                </footer>
            </body>
            <script src="../script.js" />
        </html>
    </xsl:template>
</xsl:stylesheet>