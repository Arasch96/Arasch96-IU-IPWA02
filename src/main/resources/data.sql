-- Testdaten für die Demo. MERGE INTO statt INSERT INTO, damit beim
-- nächsten Start keine Konflikte mit bereits vorhandenen IDs entstehen
-- (die Datei wird bei jedem Start erneut ausgeführt, siehe application.properties).

MERGE INTO requirement (id, title, description) VALUES
  (1, 'Login mit Benutzername und Passwort', 'Ein Nutzer soll sich mit Benutzername und Passwort anmelden können.'),
  (2, 'Passwort zurücksetzen', 'Ein Nutzer soll sein Passwort über eine E-Mail zurücksetzen können.'),
  (3, 'Suche nach Artikeln', 'Ein Nutzer soll im Shop nach Artikeln suchen können.');

MERGE INTO tester (id, name) VALUES
  (1, 'Anna Beispiel'),
  (2, 'Ben Muster'),
  (3, 'Clara Test');

MERGE INTO test_case (id, title, description, requirement_id) VALUES
  (1, 'Login mit korrekten Daten', 'Nutzer meldet sich mit gültigem Benutzernamen und Passwort an und landet auf der Startseite.', 1),
  (2, 'Login mit falschem Passwort', 'Nutzer gibt ein falsches Passwort ein, es erscheint eine Fehlermeldung.', 1),
  (3, 'Passwort-Reset-Mail anfordern', 'Nutzer fordert eine Reset-Mail an und erhält diese innerhalb weniger Minuten.', 2),
  (4, 'Suche liefert passende Treffer', 'Suche nach einem bekannten Artikelnamen liefert den erwarteten Artikel in der Trefferliste.', 3);

MERGE INTO test_run (id, name, run_date, status) VALUES
  (1, 'Sprint 1 - Regressionstest Login', '2026-09-01', 'IN_ARBEIT'),
  (2, 'Sprint 1 - Test Suche', '2026-09-05', 'GEPLANT');

MERGE INTO test_execution (id, test_run_id, test_case_id, tester_id, result) VALUES
  (1, 1, 1, 1, 'BESTANDEN'),
  (2, 1, 2, 2, 'OFFEN');

-- MERGE INTO setzt anders als ein normales INSERT den Identity-Zähler nicht
-- automatisch weiter. Ohne die folgenden Zeilen würde die nächste per Hand
-- angelegte Anforderung/Testfall/... wieder mit einer bereits vergebenen ID
-- versuchen und mit vorhandenen Datensätzen kollidieren. Da data.sql bei
-- jedem Start läuft (auch wenn schon eigene Datensätze dazugekommen sind),
-- wird hier nicht auf einen festen Wert zurückgesetzt, sondern dynamisch auf
-- die aktuell höchste vergebene ID + 1.
ALTER TABLE requirement ALTER COLUMN id RESTART WITH (SELECT COALESCE(MAX(id), 0) + 1 FROM requirement);
ALTER TABLE tester ALTER COLUMN id RESTART WITH (SELECT COALESCE(MAX(id), 0) + 1 FROM tester);
ALTER TABLE test_case ALTER COLUMN id RESTART WITH (SELECT COALESCE(MAX(id), 0) + 1 FROM test_case);
ALTER TABLE test_run ALTER COLUMN id RESTART WITH (SELECT COALESCE(MAX(id), 0) + 1 FROM test_run);
ALTER TABLE test_execution ALTER COLUMN id RESTART WITH (SELECT COALESCE(MAX(id), 0) + 1 FROM test_execution);
