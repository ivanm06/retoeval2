<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/">
        <html lang="es">
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <link rel="stylesheet" href="../styles/globals.css"/>
                <script src="https://unpkg.com/@tailwindcss/browser@4"></script>
                <title>Equipamiento</title>
            </head>
            <body>
                <header class="w-screen text-white flex flex-row justify-between items-center align-center p-6 bg-black relative">
                    <a href="../" class="text-[1.4rem] md:text-[2rem]" style='font-family: "Nosifer", serif;'>Jai<span class="text-blue-200">SKI</span>bel</a>
                    <label class="md:hidden" for="menu"><svg class="w-10 h-auto" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M3 12h18"/><path d="M3 18h18"/><path d="M3 6h18"/></svg></label>
                    <input class="hidden" type="checkbox" name="menu" id="menu"/>
                    <nav class="dp hidden absolute top-[99%] bg-[#111] p-2 px-4 z-[5] right-0 min-w-[60vw] justify-center rounded-b-md text-md">
                        <ul class="flex flex-col gap-4">
                            <li><a href="../index.html#aboutme">Nosotros</a></li>
                            <li><a href="../establecimiento.xml">Establecimientos</a></li>
                            <li><a href="../contacto.html">Contacto</a></li>
                            <li><a href="../reserva.html">Reservar</a></li>
                        </ul>
                    </nav>
                    <nav class="hidden md:block">
                        <ul class="gap-[2rem]">
                            <li><a href="../index.html#aboutme">Nosotros</a></li>
                            <li><a href="../establecimiento.xml">Establecimientos</a></li>
                            <li><a href="../contacto.html">Contacto</a></li>
                            <li><a href="../reserva.html">Reservar</a></li>
                        </ul>
                    </nav>
                </header>
                <main class="w-[90%] mx-auto lg:w-[95%] mt-10">
                    <!-- Filtros v2 -->
                    <div class="bg-gray-200 text-gray-500 flex items-center w-fit p-1 gap-2 rounded-md filtros">
                        <div>
                            <input checked="true" id="ski" name="ski" type="checkbox" class="peer hidden"/>
                            <label class="rounded-sm relative flex items-center peer-checked:bg-white peer-checked:text-black p-2 px-4 select-none cursor-pointer" for="ski">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-mountain-snow"><path d="m8 3 4 8 5-5 5 15H2L8 3z"/><path d="M4.14 15.08c2.62-1.57 5.24-1.43 7.86.42 2.74 1.94 5.49 2 8.23.19"/></svg>
                                <span>Ski</span>
                            </label>
                        </div>
                        <div>
                            <input checked="true" id="snow" name="snow" type="checkbox" class="peer hidden"/>
                            <label class="rounded-sm relative flex items-center peer-checked:bg-white peer-checked:text-black p-2 px-4 select-none cursor-pointer" for="snow">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-snowflake"><path d="m10 20-1.25-2.5L6 18"/><path d="M10 4 8.75 6.5 6 6"/><path d="m14 20 1.25-2.5L18 18"/><path d="m14 4 1.25 2.5L18 6"/><path d="m17 21-3-6h-4"/><path d="m17 3-3 6 1.5 3"/><path d="M2 12h6.5L10 9"/><path d="m20 10-1.5 2 1.5 2"/><path d="M22 12h-6.5L14 15"/><path d="m4 10 1.5 2L4 14"/><path d="m7 21 3-6-1.5-3"/><path d="m7 3 3 6h4"/></svg>
                                <span>Snowboard</span>
                            </label>
                        </div>
                        <div>
                            <input checked="true" id="acc" name="acc" type="checkbox" class="peer hidden"/>
                            <label class="rounded-sm relative flex items-center peer-checked:bg-white peer-checked:text-black p-2 px-4 select-none cursor-pointer" for="acc">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="lucide lucide-package"><path d="M11 21.73a2 2 0 0 0 2 0l7-4A2 2 0 0 0 21 16V8a2 2 0 0 0-1-1.73l-7-4a2 2 0 0 0-2 0l-7 4A2 2 0 0 0 3 8v8a2 2 0 0 0 1 1.73z"/><path d="M12 22V12"/><polyline points="3.29 7 12 12 20.71 7"/><path d="m7.5 4.27 9 5.15"/></svg>
                                <span>Accesorios</span>
                            </label>
                        </div>
                    </div>
                    <!-- Filtros -->
                    <section id="articulos" class="grow flex flex-row flex-wrap gap-10 justify-center items-center pt-20 pb-40 items-start">
                        <xsl:for-each select="//articulo">
                            <div class="articulo w-[40vh] h-fit bg-[#eee] min-h-100 rounded-md shadow-md cursor-pointer flex flex-col relative border border-black/5" data-tipo="{tipo}" data-e="{establecimiento}">
                                <div class="overflow-hidden relative bg-[#fdfdfd] min-h-[50%] flex items-center justify-center rounded-md">
                                    <img class="rounded-md" id="placeholder-{establecimiento}-{id}" src="../images/placeholder.webp" alt="Cargando imagen..."/>
                                    <img decoding="async" class="hover:scale-105 transition-transform rounded-md bg-[#fdfdfd] w-auto max-h-[40vh] place-self-center" src="./images/articulos/articulo{id}.webp" alt="JaiSKIbel" onload="this.style.display='block'; document.getElementById('placeholder-{establecimiento}-{id}').style.display='none';" style="display: none;"/>
                                    <div class="absolute top-1 right-1 px-2 flex justify-start items-center w-full gap-2">
                                        <xsl:choose>
                                            <xsl:when test="tipo = 'snowboard'">
                                                <div class="flex gap-2 w-fit">
                                                    <span class="tag"><xsl:value-of select="nivel"/></span>
                                                    <span class="inline-flex items-center rounded-full border px-2.5 py-0.5 text-xs font-semibold transition-colors focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2 border-transparent bg-gray-50 text-gray-50-foreground hover:bg-gray-50/20 shadow-md shadow-black/20"><xsl:value-of select="modalidad"/></span>
                                                </div>
                                            </xsl:when>
                                            <xsl:when test="tipo = 'ski'">
                                                <span class="inline-flex items-center rounded-full border px-2.5 py-0.5 text-xs font-semibold transition-colors focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2 border-transparent bg-gray-50 text-gray-50-foreground hover:bg-gray-50/20 shadow-md shadow-black/20"><xsl:value-of select="modalidad"/></span>
                                            </xsl:when>
                                            <xsl:otherwise test="tipo = 'accesorio'">
                                                <span class="inline-flex items-center rounded-full border px-2.5 py-0.5 text-xs font-semibold transition-colors focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2 border-transparent bg-gray-50 text-gray-50-foreground hover:bg-gray-50/20 shadow-md shadow-black/20"><xsl:value-of select="tipoAccesorio"/></span>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                        <xsl:choose>
                                            <xsl:when test="stock &gt; 0">
                                                <span class="inline-flex items-center rounded-full border px-2.5 py-0.5 text-xs font-semibold transition-colors focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2 border-transparent bg-gray-50 text-gray-50-foreground hover:bg-gray-50/20 shadow-md shadow-black/20"><xsl:value-of select="stock"/> en stock</span>
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <span class="text-white inline-flex items-center rounded-full border px-2.5 py-0.5 text-xs font-semibold transition-colors focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2 border-transparent bg-[#ef4444] text-[#ef4444]-foreground hover:bg-[#ef4444]/20 shadow-md shadow-black/20">Fuera de stock</span>
                                            </xsl:otherwise>
                                        </xsl:choose>
                                    </div>
                                </div>
                                <div class="p-4 flex-grow flex">
                                    <div class="flex flex-col gap-2 justify-between h-auto flex-grow">
                                        <h2 class="text-xl font-semibold"><xsl:value-of select="nombre"/></h2>
                                        <p class="text-gray-700"><xsl:value-of select="descripcion"/></p>
                                        <div class="flex w-full justify-between mt-4">
                                            <div class="flex items-center gap-2 text-md">
                                                <svg class="w-6 h-auto text-gray-500" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M20 13c0 5-3.5 7.5-7.66 8.95a1 1 0 0 1-.67-.01C7.5 20.5 4 18 4 13V6a1 1 0 0 1 1-1c2 0 4.5-1.2 6.24-2.72a1.17 1.17 0 0 1 1.52 0C14.51 3.81 17 5 19 5a1 1 0 0 1 1 1z"/></svg>
                                                <span><xsl:value-of select="talla"/></span>
                                            </div>
                                            <p class="font-normal text-[.9rem] text-gray-400"><span class="text-black font-bold text-[1.2rem]"><xsl:value-of select="precio"/>€</span>/dia</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </xsl:for-each>
                    </section>
                </main>
                <footer class="p-4 pt-8 flex flex-col h-auto items-center bg-black text-white gap-10 md:gap-8 text-lg md:text-md">
                    <section class="flex flex-col md:flex-row justify-between w-full items-center px-2 md:px-10 flex-wrap gap-8">
                        <div>
                            <p class="text-center text-bold my-4 md:text-lg text-xl">Enlaces</p>
                            <div class="flex flex-col justify-between">
                                <a href="./establecimiento.xml" class="text-gray-300 hover:text-gray-500">Establecimientos</a>
                                <a href="./contacto.html" class="text-gray-300 hover:text-gray-500">Contacto</a>
                                <a href="./index.html#aboutme" class="text-gray-300 hover:text-gray-500">Sobre Nosotros</a>
                            </div>
                        </div>
                        <div>
                            <p class="text-center text-bold my-4 md:text-lg text-xl">Redes Sociales</p>
                            <div class="flex items-center gap-4">
                                <a href="https://1dam3.es"><svg class="w-10 h-auto text-gray-400 hover:text-gray-500 cursor-pointer" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M22 4s-.7 2.1-2 3.4c1.6 10-9.4 17.3-18 11.6 2.2.1 4.4-.6 6-2C3 15.5.5 9.6 3 5c2.2 2.6 5.6 4.1 9 4-.9-4.2 4-6.6 7-3.8 1.1 0 3-1.2 3-1.2z"/></svg></a>
                                <a href="https://1dam3.es"><svg class="w-10 h-auto text-gray-400 hover:text-gray-500 cursor-pointer" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><rect width="20" height="20" x="2" y="2" rx="5" ry="5"/><path d="M16 11.37A4 4 0 1 1 12.63 8 4 4 0 0 1 16 11.37z"/><line x1="17.5" x2="17.51" y1="6.5" y2="6.5"/></svg></a>
                                <a href="https://1dam3.es"><svg class="w-10 h-auto text-gray-400 hover:text-gray-500 cursor-pointer" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M18 2h-3a5 5 0 0 0-5 5v3H7v4h3v8h4v-8h3l1-4h-4V7a1 1 0 0 1 1-1h3z"/></svg></a>
                            </div>
                        </div>
                    </section>
                    <div>
                        <div class="flex gap-2 justify-center mb-2">
                            <img decoding="async" src="https://mirrors.creativecommons.org/presskit/icons/by.xlarge.png" width="30" height="30" />
                            <img decoding="async" loading="lazy" src="https://mirrors.creativecommons.org/presskit/icons/nc.xlarge.png" width="30" height="30" /> 
                            <img decoding="async" loading="lazy" src="https://mirrors.creativecommons.org/presskit/icons/sa.xlarge.png" width="31" height="31" />
                        </div>
                        <p class="text-center">© 2025 JaiSKIbel. Todos los derechos reservados.</p>
                    </div>
                </footer>
            </body>
            <script src="/scripts/script.js" />
        </html>
    </xsl:template>
</xsl:stylesheet>