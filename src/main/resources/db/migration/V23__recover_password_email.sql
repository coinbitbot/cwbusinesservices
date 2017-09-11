INSERT INTO `EMAIL_TEMPLATE` (`CODE`,`TEXT`,`SUBJECT`)
  VALUES ('RECOVER_PASSWORD',
          'Good afternoon {{NAME}}<br/>Here is your new password: {{PASSWORD}}<br/>Sincerely, the administration',
            'Recover password');