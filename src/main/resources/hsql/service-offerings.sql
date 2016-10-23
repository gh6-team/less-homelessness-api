INSERT INTO organization(id, name) VALUES (1, 'St. Patrick''s Center');

INSERT INTO service_offering(id, organization_id, service, max_availability) VALUES(1, 1, 'Dental Care', 3);
INSERT INTO service_offering(id, organization_id, service, max_availability) VALUES(2, 1, 'Counseling', 20);
INSERT INTO service_offering(id, organization_id, service, max_availability) VALUES(3, 1, 'Clothing/Food', 50);

INSERT INTO client_need(id, client_id, service, approved) VALUES (1, 90077, 'Clothing/Food', true);
INSERT INTO client_need(id, client_id, service, approved) VALUES (2, 90077, 'Dental Care', false);

INSERT INTO service_need_match(client_need_id, service_offering_id) VALUES(1, 3);