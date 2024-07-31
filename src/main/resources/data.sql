INSERT INTO restaurant (id, restaurant_name, address, zip, state, country, overall_score, restaurant_peanut_score, restaurant_egg_score, restaurant_dairy_score) VALUES
  (1, 'Chez Panisse', '1517 Shattuck Ave', '94709', 'CA', 'USA', 4.50, 4.00, 4.00, 3.50),
  (2, 'Noma', 'Strandgade 93', '1401', 'Copenhagen', 'Denmark', 4.80, 4.70, 4.50, 4.60),
  (3, 'Le Bernardin', '155 W 51st St', '10019', 'NY', 'USA', 4.20, 4.00, 4.10, 3.80),
  (4, 'Osteria Francescana', 'Via Stella 22', '41121', 'Modena', 'Italy', 4.50, 4.30, 4.20, 4.00),
  (5, 'Eleven Madison Park', '11 Madison Ave', '10010', 'NY', 'USA', 4.70, 4.50, 4.80, 4.50);

INSERT INTO app_user (uid, username, city, state, zip, peanut_allergy, egg_allergy, dairy_allergy) VALUES
  (1, 'john_doe', 'New York', 'NY', '10001', false, false, true),
  (2, 'jane_smith', 'San Francisco', 'CA', '94105', true, false, false),
  (3, 'bob_jones', 'Chicago', 'IL', '60604', false, true, false),
  (4, 'alice_brown', 'Boston', 'MA', '02110', true, true, false),
  (5, 'charlie_white', 'Seattle', 'WA', '98101', false, false, false);

INSERT INTO review (id, restaurant_id, user_id, description, peanut_score, egg_score, dairy_score, review_status) VALUES
  (1, 1, 1, 'Great place with good variety of dishes.', 5, 4, 3, 'PENDING'),
  (2, 2, 2, 'The food was average and not very impressive.', 2, 2, 2, 'PENDING'),
  (3, 3, 3, 'Wonderful atmosphere and excellent service.', 4, 5, 4, 'REJECTED'),
  (4, 4, 4, 'Poor experience, food was cold and service was lacking.', 1, 1, 1, 'APPROVED'),
  (5, 5, 5, 'The restaurant has a cozy atmosphere, but the food was not up to the mark.', 3, 3, 2, 'APPROVED');

