INSERT INTO `EMAIL_TEMPLATE` (`CODE`,`TEXT`,`SUBJECT`)
  VALUES ('EMAIL_FROM_CONTACT_PAGE',
          'User write a message on contact page<Br />Name: {{USER_NAME}}<Br />Email: {{EMAIL}}<br/>Text: {{TEXT}}',
            'New message from contact form');