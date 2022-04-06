module.exports = {
  purge: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
      textColor: {
        "main": "#020814",
        "red": "#C74157",
        "sec": "#445b78",
        "light": "#798EAA"
      },
      colors : {
        "white-main": "#f3f5f9",
        'white-sec': "#f1f8ff",
        "grey-main": "#c8ccfd",
        "red": "#C74157"
      },
      fontFamily: {
        sans: 'Poppins, sans-serif',
        number: 'Open sans, sans-serif'
      },
    },
  },
  variants: {
    extend: {},
  },
  plugins: [],
}
