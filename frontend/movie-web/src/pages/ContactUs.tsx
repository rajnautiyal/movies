import '@popperjs/core/dist/cjs/popper.js';
import '../assets/contactus.css';

function ContactUsPage() {
  return (
    <main className="container mt-5">
        <h3>Contact Us</h3>
        <p>We would love to hear from you. Fill out the form below to get in touch with us.</p>

        <div className="row">
            <div className="col-md-6 contact-form">
                <form action="#" method="POST">
                    <div className="mb-3">
                        <label htmlFor="name" className="form-label">Your Name</label>
                        <input type="text" className="form-control" id="name" name="name" required/>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="email" className="form-label">Your Email</label>
                        <input type="email" className="form-control" id="email" name="email" required/>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="message" className="form-label">Message</label>
                        <textarea className="form-control" id="message" name="message" required></textarea>
                    </div>
                    <button type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
            <div id="address" className="col-md-6 contact">
                <h4>Address</h4>
                <p>Feltham, Middlesex</p>
                <h4>Phone</h4>
                <p> - - </p>
                <h4>Email</h4>
                <p>movie@example.com</p>
            </div>
        </div>
    </main>
  );
};

export default ContactUsPage;
