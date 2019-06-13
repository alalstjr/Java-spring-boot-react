import React from 'react'
import { Link } from "react-router-dom"

export default function Navbar() {
    return (
        <nav className="navbar navbar-expand-sm navbar-dark bg-primary mb-4">
            <div className="container">
                <Link to="/" className="navbar-brand">
                    Project Task Tool
                </Link>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#mobile-nav">
                    <span className="navbar-toggler-icon" />
                </button>
            </div>
        </nav>
    )
}
