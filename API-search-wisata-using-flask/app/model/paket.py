from app import db


class Paket(db.Model):
    package_id = db.Column(db.String(5), primary_key=True)
    city = db.Column(db.String(50), nullable=False)
    place_id1 = db.Column(db.String(255), nullable=False)
    place_id2 = db.Column(db.String(255), nullable=False)
    place_id3 = db.Column(db.String(255), nullable=False)
    place_id4 = db.Column(db.String(255), nullable=False)
    place_id5 = db.Column(db.String(255), nullable=False)

    def __repr__(self):
        return '<Paket {}>'.format(self.name)
