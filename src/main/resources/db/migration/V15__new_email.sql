INSERT INTO `EMAIL_TEMPLATE` (`CODE`,`TEXT`,`SUBJECT`)
  VALUES ('NEW_REQUEST_TO_USER',
          'Good afternoon {{NAME}}<br/> We received your request. Our managers start working with your application.<br/> More details you can get in your office on our platform. To do this, use the link: <a href = "{{LINK}}"> cabinet </a><br/>Sincerely, the administration',
            'Your request has been received');