# Require4Testing

Aufgabenstellung:
Du arbeitest im Web-Development-Team eines kleinen Startups. Es soll ein neues Projekt mit dem Namen „Require4Testing“ gestartet werden, mit dem Ziel, eine Web-App zur Organisation manueller Anwendertests zu entwickeln. Der Requirements Engineer hat sich bereits im Vorfeld mit Personen aus ausgewählten Projekten ausgetauscht, die für die Planung, Erstellung und Durchführung von Tests verantwortlich sind. Im Ergebnis hat er die folgenden User Stories im Product Backlog hinterlegt und nach der MoSCoW-Methode priorisiert:

1.
MUST: Als Requirements Engineer möchte ich zu testende Anforderungen erstellen können.
2.
MUST: Als Testmanager:in möchte ich Testläufe anlegen können.
3.
MUST: Als Testfallersteller:in möchte ich zu einer Anforderung Testfälle erstellen können.
4.
SHOULD: Als Testmanager:in möchte ich einem Testlauf verschiedene Testfälle und eine:n Tester:in zuordnen können.
5.
SHOULD: Als Tester:in möchte ich mir zugeordnete Testfälle mit einem Ergebnis versehen können.
6.
COULD: Als Tester:in möchte ich einen Überblick über mir zugewiesene Testfälle haben.
7.
COULD: Als Testmanager:in möchte ich einen Überblick über den Status aller Testdurchführungen haben.
8.
COULD: Als Testfallersteller:in möchte ich zu Testfällen einzelne Testschritte erfassen.

Aufgabenstellung:
1. Richte ein öffentliches Code-Repository für Dein Projekt ein, z. B. in GitHub.
2. Entscheide Dich für fünf Anforderungen aus dem Product Backlog, die im ersten Sprint prototypisch umgesetzt werden sollen.
3. Entwickle einen Prototyp für die Webanwendung, der die ausgewählten Funktionen rudimentär anbietet. Achte dabei auf die persistente Datenhaltung. Nutze dabei den Technologiestack aus dem Skript:
− JSF und CDI/Beans, optional dazu: andere Komponentenbibliotheken (z. B. PrimeFaces);
− JPA mit einem Persistenz-Provider (z. B. Hibernate);
− eine relationale Datenbank (z. B. MySQL);
– Alternative: Verwende Spring bzw. Spring Boot mit Spring Beans anstelle von JSF mit CDI Beans. Nutze auch in diesem Falle JPA oder Spring Data JPA zusammen mit einem JPA-Provider und einer relationalen Datenbank.
4. Dokumentiere die Entwicklung der Webanwendung. Im Hauptteil sollten das Design und die Umsetzung beschrieben werden. Dabei soll jeweils im Entwurf und anhand von Screenshots in der tatsächlichen Umsetzung gezeigt werden, wie …
− … die einzelnen Webseiten gestaltet sind, und wie man zwischen den Webseiten hin- und herspringen kann,
− wie die Datenbankstruktur beschaffen ist und
− wie die softwaretechnische Architektur gestaltet ist (mind. ein UML-Strukturdiagramm inkl. aller verwendeter Beans).

Hinweis: Von einem Prototyp erwartet der Product Owner eine App, die eine pragmatische, eher funktional ausgerichtete Gestaltung der Nutzeroberfläche aufweist. Das Ergebnis muss also keinen Design Award gewinnen.

## Stack

- Java 17
- Spring Boot (Spring MVC, Controller/Repositories als Beans)
- Thymeleaf für die Views
- Spring Data JPA + Hibernate
- H2, Dateimodus
- Bootstrap 5 per CDN

Laut Aufgabenstellung wäre auch JSF + CDI möglich gewesen, ich habe mich aber für die im Skript genannte Alternative Spring Boot entschieden, weil ich das aus der Arbeit kenne.

## Was fehlt?

- Login/Rollen
- Validierung mit Fehlermeldungen im Formular
- Bearbeiten/Löschen (aktuell nur Anlegen + Ergebnis ändern)
- Tests
