from app import db


class Wisata(db.Model):
    place_id = db.Column(db.String(255), primary_key=True)
    name = db.Column(db.String(50), nullable=False)
    category = db.Column(db.String(30), nullable=False)
    city = db.Column(db.String(30), nullable=False)
    price = db.Column(db.String(7), nullable=False)
    rating = db.Column(db.String(5), nullable=False)
    spend_time = db.Column(db.String(5), nullable=False)
    deskripsi = db.Column(db.String(500), nullable=False)

    def __repr__(self):
        return '<Wisata {}>'.format(self.name)
