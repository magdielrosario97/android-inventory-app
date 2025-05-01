# Inventory App

A lightweight, offline inventory management tool built for individuals and small business owners. The app allows users to track items, manage stock levels, and receive SMS-based low-stock notifications through a clean, functional interface that works without internet access.

## Features

- User authentication (Login / Registration)
- View inventory in a dynamic grid layout
- Add, edit, and delete inventory items
- SMS permission screen for low-stock alerts
- Minimal, user-centered UI designed for speed and accessibility
- Fully functional offline — no internet required

## Screens

- **Login / Registration**  
  Secure user access with simple credential management

- **Inventory Dashboard**  
  All items displayed in a responsive grid view with stock details

- **Edit Item Screen**  
  Dynamically handles both adding and editing items using a flag passed via `Intent`

- **SMS Settings**  
  Optional screen for enabling SMS permissions

## Design Approach

Built with a focus on simplicity and usability. Every screen is optimized for quick navigation, clear labeling, and minimal input friction. UI decisions were made to ensure ease of use, even on smaller screens or when the keyboard is active.

## Development Strategy

- Developed screen-by-screen with small, focused tasks  
- Built core functionality first before refining the UI  
- Used modular code and dynamic data flow to reduce duplication  
- Implemented data refresh on return using `onResume()` for consistency

## Testing

Tested on both emulator and physical Android devices to catch layout and behavior issues. Frequent testing during development ensured feature stability after each change.

## Key Implementation

CRUD functionality for inventory items connected to a local SQLite database using DAO patterns. Integrated seamlessly with the UI to provide a usable and reliable experience.

## Tech Stack

- **Language:** Java  
- **IDE:** Android Studio  
- **Database:** SQLite  
- **Architecture:** DAO, Intent-based navigation  
- **Other:** SMS permissions, lifecycle management, offline-first design
