---

# **Webes adatkezelő környezetek**

## **Féléves feladat**

**A feladat címe:**  
**XML dokumentumok DOM API alapú feldolgozása**  

**Készítette:** Lakatos Alfa Rómeó  
**Neptunkód:** HELL666  

**Dátum:** 2024. december 3.

---

## **Tartalomjegyzék**

1. [Bevezetés](#bevezetés)
2. [Első feladat: XML dokumentum előkészítése](#első-feladat-xml-dokumentum-előkészítése)
   1. [ER modell](#er-modell)
   2. [XDM modell](#xdm-modell)
   3. [XML dokumentum](#xml-dokumentum)
   4. [XMLSchema](#xmlschema)
3. [Második feladat: DOM API programozás](#második-feladat-dom-api-programozás)
   1. [Adatolvasás](#adatolvasás)
   2. [Adatírás](#adatírás)
   3. [Adatlekérdezés](#adatlekérdezés)
   4. [Adatmódosítás](#adatmódosítás)
4. [Következtetés](#következtetés)

---

## **Bevezetés**

A modern informatikai rendszerek egyik legfontosabb alapköve a strukturált adatkezelés, különös tekintettel az adatok tárolására és cseréjére. Az XML (Extensible Markup Language) széles körben alkalmazott szabvány, amely lehetővé teszi az adatok hierarchikus szervezését, interoperabilitását és rugalmasságát.

A DOM (Document Object Model) API a webes technológiák egyik legismertebb programozási interfésze, amely az XML dokumentumok fa struktúráját dolgozza fel. A projekt során a DOM API segítségével négy fő feladatot oldottunk meg:

- Az XML dokumentum olvasása,
- Az XML dokumentum írása,
- Az adatok lekérdezése,
- Az adatok módosítása.

A projekt központi témája egy **gépjárműkölcsönző rendszer**, amely az XML segítségével tárolja és kezeli az adatokat. A rendszer modelljét először ER modell formájában terveztük meg, majd XDM modellé alakítottuk, és végül XML dokumentumként implementáltuk. Az adatstruktúra validációját egy XSD séma biztosítja, amely az adatok integritását garantálja.

---

## **Első feladat: XML dokumentum előkészítése**

### **ER modell**

Az adatbázis ER modellje egy **Gépjármű** központi entitás köré szerveződik, amelyhez kapcsolódnak a karbantartási rekordok, kölcsönzések, vásárlók és kölcsönző cégek adatai.

#### **Kapcsolatok:**

- **Gépjármű – Karbantartás**: Egy gépjárműhöz több karbantartás tartozhat (1:N).
- **Karbantartás – Szerelő**: Egy karbantartás egy szerelőhöz kötött (1:N).
- **Gépjármű – Kölcsönzés**: Egy gépjármű több kölcsönzésben szerepelhet (1:N).
- **Kölcsönzés – Vásárló**: Egy vásárló több kölcsönzéshez kapcsolódhat (N:1).
- **Kölcsönzés – Kölcsönző cég**: Egy kölcsönzés egy adott cégnél történik (N:1).

#### **Attribútumok:**

- **Gépjármű**: JárműID, Rendszám, Márka, Típus, GyártásiHely, Évjárat, MegtettKilométer.
- **Karbantartás**: KarbantartásiSzint, MegtettKilométer.
- **Szerelő**: SzerelőID, Név, SSN, Lakcím, Telefonszám.
- **Kölcsönzés**: KölcsönzésID, Időpont, NapokSzáma, Ár.
- **Vásárló**: VásárlóID, Név, Lakcím, Telefonszám.
- **Kölcsönző cég**: CégID, Név, AutókSzáma, Alapterület.

#### **ER modell ábra**:

_(Az ER modell ábra beszúrásra kerül - `ERNLFUA8.png`)_

---

### **XDM modell**

Az XDM modell az ER modell hierarchikus átalakításával jött létre. Az átalakítás során az entitások elemekké váltak, míg a kapcsolatok attribútumokon keresztül valósultak meg. A modellezés során a fő elem az `<AutoRendszer>`, amely tartalmazza a rendszer összes többi komponensét.

#### **XDM modell ábra**:

_(Az XDM modell ábra beszúrásra kerül - `XDMNLFUA8.png`)_

---

### **XML dokumentum**

Az XDM modell alapján készült XML dokumentum a gépjárműkölcsönző rendszer példadatait tartalmazza.

#### **Példa XML dokumentum:**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<AutoRendszer>
    <Gepjarmu JarmuID="1">
        <Rendszam>ABC-123</Rendszam>
        <JarmuTipus>Személyautó</JarmuTipus>
        <GyartasiHely>Budapest</GyartasiHely>
        <MegtettKilometer>120000</MegtettKilometer>
    </Gepjarmu>
    <Gepjarmu JarmuID="2">
        <Rendszam>XYZ-456</Rendszam>
        <JarmuTipus>Teherautó</JarmuTipus>
        <GyartasiHely>Győr</GyartasiHely>
        <MegtettKilometer>90000</MegtettKilometer>
    </Gepjarmu>
</AutoRendszer>
```

_(Teljes fájl mellékelve: `XMLNLFUA8.xml`)_

---

### **XMLSchema**

Az XSD séma biztosítja az XML dokumentum validációját az alábbi módon:

- Minden entitás attribútumainak típusait meghatározza.
- A kulcsokat (`key`) és referencia-kulcsokat (`keyref`) definiálja.

#### **Példa séma részlet:**

```xml
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xs:element name="AutoRendszer">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="Gepjarmu" maxOccurs="unbounded">
                    <xs:complexType>
                        <xs:sequence>
                            <xs:element name="Rendszam" type="xs:string"/>
                            <xs:element name="JarmuTipus" type="xs:string"/>
                        </xs:sequence>
                        <xs:attribute name="JarmuID" type="xs:string" use="required"/>
                    </xs:complexType>
                </xs:element>
            </xs:sequence>
        </xs:complexType>
    </xs:element>
</xs:schema>
```

_(Teljes fájl mellékelve: `XMLSchemaNLFUA8.xsd`)_

---

### **Második feladat: DOM API programozás**

A második feladat során a DOM API-t használva négy különböző programot valósítottunk meg, amelyek az XML dokumentum feldolgozásának különböző aspektusait fedik le. A feladatok: adatolvasás, adatírás, adatlekérdezés és adatmódosítás. Minden program a megfelelő Java osztályban került implementálásra, a specifikációk szerint.

---

### **2.1 Adatolvasás**

A `DOMReadNeptunkod.java` osztály célja, hogy az XML dokumentumot beolvassa, és annak tartalmát hierarchikus fa struktúrában a konzolra írja. A program a DOM API segítségével dolgozza fel az XML elemeit és attribútumait.

#### **Fő lépések:**

1. Az XML dokumentum betöltése a `DocumentBuilder` segítségével.
2. A gyökérelem és alárendelt elemek rekurzív feldolgozása.
3. Az elemek attribútumainak és értékeinek kiírása blokk formában.
4. A dokumentum tartalmának mentése egy új XML fájlba.

#### **Fontos kód:**

```java
NodeList nodeList = document.getElementsByTagName("Gepjarmu");
for (int i = 0; i < nodeList.getLength(); i++) {
    Element element = (Element) nodeList.item(i);
    System.out.println("Jármű ID: " + element.getAttribute("JarmuID"));
    System.out.println("Rendszám: " + element.getElementsByTagName("Rendszam").item(0).getTextContent());
}
```

#### **Kimenet a konzolon:**

```
Element: Gepjarmu
  Attribute: JarmuID = 1
  Rendszám: ABC-123
  Jármű típus: Személyautó
Element: Gepjarmu
  Attribute: JarmuID = 2
  Rendszám: XYZ-456
  Jármű típus: Teherautó
```

_(A teljes kód mellékelve: `DOMReadNeptunkod.java`)_

---

### **2.2 Adatírás**

A `DOMWriteNeptunkod.java` osztály egy új XML dokumentumot generál a DOM API segítségével, és azt fájlba menti. A program lehetővé teszi új elemek és attribútumok hozzáadását a meglévő hierarchiához.

#### **Fő lépések:**

1. Egy új DOM dokumentum létrehozása.
2. A gyökérelem (`AutoRendszer`) létrehozása és hozzáadása.
3. Új jármű adatok (`Gepjarmu`) hozzáadása, attribútumokkal és alárendelt elemekkel.
4. Az eredmény mentése egy XML fájlba.

#### **Fontos kód:**

```java
Element root = document.createElement("AutoRendszer");
document.appendChild(root);

Element gepjarmu = document.createElement("Gepjarmu");
gepjarmu.setAttribute("JarmuID", "3");
Element rendszam = document.createElement("Rendszam");
rendszam.setTextContent("DEF-789");
gepjarmu.appendChild(rendszam);
root.appendChild(gepjarmu);
```

#### **Kimenet fájlban:**

```xml
<AutoRendszer>
    <Gepjarmu JarmuID="3">
        <Rendszam>DEF-789</Rendszam>
        <JarmuTipus>Kisbusz</JarmuTipus>
        <GyartasiHely>Szeged</GyartasiHely>
        <MegtettKilometer>45000</MegtettKilometer>
    </Gepjarmu>
</AutoRendszer>
```

_(A teljes kód mellékelve: `DOMWriteNeptunkod.java`)_

---

### **2.3 Adatlekérdezés**

A `DOMQueryNeptunkod.java` osztály legalább négy különböző lekérdezést valósít meg az XML dokumentum adataiból. A lekérdezések nem használják az XPath kifejezéseket, hanem a DOM API elemeire épülnek.

#### **Lekérdezések:**

1. Összes jármű típusa kiíratása.
2. Egy adott jármű adatai ID alapján.
3. Összes karbantartási rekord listázása.
4. Vásárlók nevei és címei.

#### **Fontos kód:**

```java
NodeList gepjarmuvek = document.getElementsByTagName("Gepjarmu");
for (int i = 0; i < gepjarmuvek.getLength(); i++) {
    Element gepjarmu = (Element) gepjarmuvek.item(i);
    System.out.println("Jármű típus: " + gepjarmu.getElementsByTagName("JarmuTipus").item(0).getTextContent());
}
```

#### **Kimenet a konzolon:**

```
Jármű típus: Személyautó
Jármű típus: Teherautó
```

_(A teljes kód mellékelve: `DOMQueryNeptunkod.java`)_

---

### **2.4 Adatmódosítás**

A `DOMModifyNeptunkod.java` osztály az XML dokumentum meglévő elemeit módosítja. Legalább négy különböző módosítást valósít meg.

#### **Módosítások:**

1. Egy jármű megtett kilométerének frissítése.
2. Egy új jármű hozzáadása.
3. Egy kölcsönzés áránek módosítása.
4. Egy szerelő nevének frissítése.

#### **Fontos kód:**

```java
NodeList gepjarmuvek = document.getElementsByTagName("Gepjarmu");
for (int i = 0; i < gepjarmuvek.getLength(); i++) {
    Element gepjarmu = (Element) gepjarmuvek.item(i);
    if (gepjarmu.getAttribute("JarmuID").equals("1")) {
        gepjarmu.getElementsByTagName("MegtettKilometer").item(0).setTextContent("125000");
    }
}
```

#### **Kimenet fájlban (részlet):**

```xml
<Gepjarmu JarmuID="1">
    <Rendszam>ABC-123</Rendszam>
    <MegtettKilometer>125000</MegtettKilometer>
</Gepjarmu>
```

_(A teljes kód mellékelve: `DOMModifyNeptunkod.java`)_

---

## **Következtetés**

A feladat megoldása során alaposan megismerkedtünk az XML szabvány és a DOM API lehetőségeivel. A projekt során az alábbiakat tanultuk:

- Hogyan lehet egy relációs adatbázist XML formátumba alakítani (ER és XDM modellek).
- Hogyan lehet a DOM API segítségével az XML dokumentumot olvasni, írni, lekérdezni és módosítani.
- Az XSD séma validációjának fontosságát és alkalmazását.

A feladat kiváló alapot adott az XML és a kapcsolódó technológiák mélyebb megértéséhez, valamint a DOM API hatékony használatához.

---

**Mellékletek:**

- ER modell ábra: `ERNLFUA8.png`
- XDM modell ábra: `XDMNLFUA8.png`
- XML dokumentum: `XMLNLFUA8.xml`
- XML séma: `XMLSchemaNLFUA8.xsd`
- Java kódok: `DOMReadNeptunkod.java`, `DOMWriteNeptunkod.java`, `DOMQueryNeptunkod.java`, `DOMModifyNeptunkod.java`

---
